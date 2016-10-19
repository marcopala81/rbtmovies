package ie.brandtone.moviescomparator.wsclient.impl;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_FOUND_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.OMDB_API_GET_REQUEST_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.OMDB_API_RESPONSE_STATUS_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.OMDB_RESPONSE_FIELD_ERROR_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.REQUESTED_TITLE_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.RESPONSE_STATUS_ERROR_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.UNEXPECTED_TITLE_ERROR_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.WS_CLIENT_ERROR_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getEnteringMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getExitingMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getLeavingMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;
import static ie.brandtone.moviescomparator.utils.Commons.nestedExceptionToString;
import static ie.brandtone.moviescomparator.wsclient.impl.OMDbApiConfig.OMDB_API_URL_KEY;
import static ie.brandtone.moviescomparator.wsclient.impl.OMDbApiConfig.OMDB_RESPONSE_FIELD_KEY;
import static ie.brandtone.moviescomparator.wsclient.impl.OMDbApiConfig.OMDB_RESPONSE_FOUND_KEY;
import static ie.brandtone.moviescomparator.wsclient.impl.OMDbApiConfig.OMDB_TITLE_PARAM_KEY;
import static javax.ws.rs.core.Response.Status.OK;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import ie.brandtone.moviescomparator.dao.entity.MovieEntity;
import ie.brandtone.moviescomparator.dao.exception.BadMovieFormatException;
import ie.brandtone.moviescomparator.wsclient.MovieRetrieverService;
import ie.brandtone.moviescomparator.wsclient.exception.MovieNotFoundException;
import ie.brandtone.moviescomparator.wsclient.exception.WsClientConfigException;
import ie.brandtone.moviescomparator.wsclient.exception.WsClientException;

/**
 * Implements the client for the <i>OMDb API</i> webservice (adhere to the <i>Singleton</i> pattern). 
 * 
 * @author Marco Pala
 */
@Service
public class OMDbApiRestClient implements MovieRetrieverService
{
    /**
     * The configuration reader for the OMDb API webservice.
     */
    private static OMDbApiConfig config = new OMDbApiConfig();
    
    /**
     * The <i>Singleton</i> instance for the OMDb API webservice.
     */
    private static MovieRetrieverService instance;

    /**
     * The Jersey client for the OMDb API webservice.
     */
    private WebResource webResource;

    /**
     * The Apache Log4j logger.
     */
    private static final Logger LOGGER = Logger.getLogger(OMDbApiRestClient.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieEntity getMovieByTitle(String title) throws MovieNotFoundException
    {
        String methodName = "getMovieByTitle";
        LOGGER.info(getEnteringMessage(methodName));
        LOGGER.info(getMessageFromBundle(REQUESTED_TITLE_MSG_KEY, title));
                
        MovieEntity movie = null;
        
        try
        {
            // Prepare the query string for the GET method
            MultivaluedMap<String, String> inputTitle = new MultivaluedMapImpl();
            inputTitle.add(config.getOMDbApiProperty(OMDB_TITLE_PARAM_KEY), title);

            // GET method
            LOGGER.info(getMessageFromBundle(OMDB_API_GET_REQUEST_MSG_KEY));
            ClientResponse response = null;
            try
            {
                Client wsclient = Client.create();
                webResource = wsclient.resource(config.getOMDbApiProperty(OMDB_API_URL_KEY));                
                response = webResource.queryParams(inputTitle).get(ClientResponse.class);
            }
            catch (UniformInterfaceException | ClientHandlerException e)
            {
                LOGGER.error(getMessageFromBundle(WS_CLIENT_ERROR_MSG_KEY));
                LOGGER.info(getLeavingMessage(methodName));
                throw new MovieNotFoundException(new WsClientException(e), title);
            }

            // HTTP status code (enumerated)
            Response.Status status = Response.Status.fromStatusCode(response.getStatus());
            LOGGER.info(getMessageFromBundle(OMDB_API_RESPONSE_STATUS_MSG_KEY, String.valueOf(status.getStatusCode()), status.name()));

            // Check HTTP 200/OK response; otherwise throw a MovieNotFoundException
            if (status != OK)
            {
                LOGGER.error(getMessageFromBundle(RESPONSE_STATUS_ERROR_MSG_KEY));
                LOGGER.info(getLeavingMessage(methodName));
                throw new MovieNotFoundException(new WsClientException(status.getStatusCode()), title);
            }

            // Raw response entity (String)
            String responseOMDbEntity = response.getEntity(String.class);
            LOGGER.debug(responseOMDbEntity);

            // JSON String response converted to a Jersey JSON object
            JSONObject omdbJsonMovie = new JSONObject(responseOMDbEntity);
            String responseFiledKey = config.getOMDbApiProperty(OMDB_RESPONSE_FIELD_KEY);
            String responseField = omdbJsonMovie.getString(responseFiledKey);

            // Check "Response" field; if 'false' throw a MovieNotFoundException
            if (!config.getOMDbApiProperty(OMDB_RESPONSE_FOUND_KEY).equals(responseField))
            {
                LOGGER.error(getMessageFromBundle(OMDB_RESPONSE_FIELD_ERROR_MSG_KEY, responseFiledKey, responseField, title));
                LOGGER.info(getLeavingMessage(methodName));
                throw new MovieNotFoundException(title);
            }

            // Fill the movie object
            movie = AbstractOMDbMovieFactory.getMovieFromOMDbJson(omdbJsonMovie);
        }
        catch (WsClientConfigException | BadMovieFormatException e)
        {
            // Unexpected error during retrieval
            LOGGER.error(nestedExceptionToString(e));
            LOGGER.info(getLeavingMessage(methodName));
            throw new MovieNotFoundException(e, title);
        }

        // Doublecheck the movie title because the OMDb API will return movies also
        // when the title partially matches the searched one
        if (!movie.getTitle().equalsIgnoreCase(title))
        {
            LOGGER.error(getMessageFromBundle(UNEXPECTED_TITLE_ERROR_MSG_KEY, movie.getTitle(), title));
            LOGGER.info(getLeavingMessage(methodName));
            throw new MovieNotFoundException(title);
        }
        else
        {
            LOGGER.info(getMessageFromBundle(MOVIE_FOUND_MSG_KEY, movie.toString()));
        }
        
        LOGGER.info(getExitingMessage(methodName));

        return movie;
    }

    /**
     * Get the OMBb API REST client <i>Singleton</i> instance (cast to <code>MovieRetrieverService</code>).
     * 
     * @return The OMBb API REST client <i>Singleton</i> instance
     * 
     * @throws WsClientException in case of any error during the initialization of the REST client.
     */
    public static MovieRetrieverService getInstance() throws WsClientException
    {
        // Initialize only once
        if (instance == null)
        {
            initWsClient();
        }

        return instance;
    }

    /**
     * Initializes the REST client for the OMDb API webservice.
     * 
     * @throws WsClientException in case of any initialization problem
     */
    private static void initWsClient() throws WsClientException
    {
        // Initialize the Singleton instance
        instance = (MovieRetrieverService) new OMDbApiRestClient();

    }
}
