package ie.brandtone.moviescomparator.wsclient;

import static javax.ws.rs.core.Response.Status.OK;
import static ie.brandtone.moviescomparator.wsclient.Constants.OMDB_API_URL;
import static ie.brandtone.moviescomparator.wsclient.Constants.OMDB_TITLE_PARAM;
import static ie.brandtone.moviescomparator.wsclient.Constants.OMDB_RESPONSE_FIELD;
import static ie.brandtone.moviescomparator.wsclient.Constants.OMDB_RESPONSE_FOUND;

import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

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
	 * {@inheritDoc}
	 */
	@Override
	public Movie getMovieByTitle(String title) throws MovieNotFoundException, BadMovieFormatException
	{
		// Prepare the query string for the GET method
		MultivaluedMap<String, String> inputTitle = new MultivaluedMapImpl();
		inputTitle.add(OMDB_TITLE_PARAM, title);
		
		// GET method
		ClientResponse response = webResource.queryParams(inputTitle).get(ClientResponse.class);
		
		// HTTP status code
		int status = response.getStatus();

		// Check HTTP 200/OK response; otherwise throw a MovieNotFoundException
		if (status != OK.getStatusCode())
		{
			throw new MovieNotFoundException(new WsClientException(status), title);
		}
		
		// JSON String response converted to a Jersey JSON object
		JSONObject jObject = new JSONObject(response.getEntity(String.class));
		
		// Check "Response" field; if 'false' throw a MovieNotFoundException
		if (!OMDB_RESPONSE_FOUND.equals(jObject.getString(OMDB_RESPONSE_FIELD)))
		{
			throw new MovieNotFoundException(title);
		}
				
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
