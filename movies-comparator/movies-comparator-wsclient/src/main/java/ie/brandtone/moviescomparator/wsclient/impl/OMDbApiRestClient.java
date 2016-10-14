package ie.brandtone.moviescomparator.wsclient.impl;

import static ie.brandtone.moviescomparator.utils.Constants.OMDB_API_URL;
import static ie.brandtone.moviescomparator.utils.Constants.OMDB_RESPONSE_FIELD;
import static ie.brandtone.moviescomparator.utils.Constants.OMDB_RESPONSE_FOUND;
import static ie.brandtone.moviescomparator.utils.Constants.OMDB_TITLE_PARAM;
import static ie.brandtone.moviescomparator.utils.Constants.REQUESTED_TITLE_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Constants.OMDB_API_GET_REQUEST_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Constants.OMDB_API_RESPONSE_STATUS_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Constants.RESPONSE_STATUS_ERROR_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Constants.OMDB_RESPONSE_FIELD_ERROR_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;
import static ie.brandtone.moviescomparator.utils.Commons.getEnteringMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getExitingMessage;
import static javax.ws.rs.core.Response.Status.OK;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import ie.brandtone.moviescomparator.dao.Movie;
import ie.brandtone.moviescomparator.dao.exception.BadMovieFormatException;
import ie.brandtone.moviescomparator.wsclient.exception.MovieNotFoundException;
import ie.brandtone.moviescomparator.wsclient.MovieRetrieverService;
import ie.brandtone.moviescomparator.wsclient.exception.WsClientException;

/**
 * Implements the client for the <i>OMDb API</i> webservice (adhere to the <i>Singleton</i> pattern). 
 * 
 * @author Marco Pala
 */
public class OMDbApiRestClient implements MovieRetrieverService
{	
	/**
	 * The Singleton instance of this webservice client.
	 */
	private static MovieRetrieverService instance;
	
	/**
	 * The Jersey client for for the OMDb API webservice.
	 */
	private static WebResource webResource;
	
	/**
	 * The Apache Log4j logger.
	 */
	private final static Logger logger = Logger.getLogger(OMDbApiRestClient.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Movie getMovieByTitle(String title) throws MovieNotFoundException, BadMovieFormatException
	{
		String methodName = "getMovieByTitle";
		logger.info(getEnteringMessage(methodName));
		logger.info(getMessageFromBundle(REQUESTED_TITLE_MSG_KEY, title));
		
		// Prepare the query string for the GET method
		MultivaluedMap<String, String> inputTitle = new MultivaluedMapImpl();
		inputTitle.add(OMDB_TITLE_PARAM, title);
		
		// GET method
		logger.info(getMessageFromBundle(OMDB_API_GET_REQUEST_MSG_KEY));
		ClientResponse response = webResource.queryParams(inputTitle).get(ClientResponse.class);
		
		// HTTP status code (enumerated)
		Response.Status status = Response.Status.fromStatusCode(response.getStatus());
		logger.info(getMessageFromBundle(OMDB_API_RESPONSE_STATUS_MSG_KEY, status.name()));
		
		// Check HTTP 200/OK response; otherwise throw a MovieNotFoundException
		if (status != OK)
		{
			logger.error(getMessageFromBundle(RESPONSE_STATUS_ERROR_MSG_KEY));
			throw new MovieNotFoundException(new WsClientException(status.getStatusCode()), title);
		}
		
		// Raw response entity (String)
		String responseEntity = response.getEntity(String.class);
		logger.debug(responseEntity);
		
		// JSON String response converted to a Jersey JSON object
		JSONObject jObject = new JSONObject(responseEntity);
		String responseField = jObject.getString(OMDB_RESPONSE_FIELD);
		
		// Check "Response" field; if 'false' throw a MovieNotFoundException
		if (!OMDB_RESPONSE_FOUND.equals(responseField))
		{
			logger.error(getMessageFromBundle(OMDB_RESPONSE_FIELD_ERROR_MSG_KEY, OMDB_RESPONSE_FIELD, responseField, title));
			throw new MovieNotFoundException(title);
		}

		logger.info(getExitingMessage(methodName));
		
		return new Movie(jObject);
	}
	
	/**
	 * Get the OMBb API REST client Singleton instance (cast to <code>MovieRetrieverService</code>).
	 * 
	 * @return The OMBb API REST client Singleton instance
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
	 */
	private static void initWsClient() throws WsClientException
	{
		// Initialize the Singleton instance
		instance = (MovieRetrieverService) new OMDbApiRestClient();
		try
		{
			Client wsclient = Client.create();
			webResource = wsclient.resource(OMDB_API_URL);
		}
		catch (Exception e)
		{
			throw new WsClientException(e);
		}
	}
}
