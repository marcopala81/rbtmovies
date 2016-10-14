package ie.brandtone.moviescomparator.wsclient.impl;

import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;
import static ie.brandtone.moviescomparator.utils.Constants.FILE_NOT_FOUND_ERROR_MSG_KEY;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ie.brandtone.moviescomparator.wsclient.exception.WsClientConfigException;
import ie.brandtone.moviescomparator.wsclient.exception.WsClientException;

/**
 * This class holds all the relevant literals and constants of the OMDb API webservice configuration.
 * 
 * @author Marco Pala
 */
public final class OMDbApiConfig
{
	/**
	 * The OMDb Api configuration file (Singleton).
	 */
	private static Properties omdbApiConfigFile;
	
	/**
	 * The OMDb Api configuration filename. 
	 */
	public static final String OMDB_CONFIG_FILENAME = "omdbapi.config.properties";
		
	/**
	 * The key to retrieve the OMDb API URL.
	 */
	public static final String OMDB_API_URL_KEY = "omdbapi.config.url";
	
	/**
	 * The key to put the title parameter on a OMDb API request.
	 */
	public static final String OMDB_TITLE_PARAM_KEY = "omdbapi.param.title";
	
	/**
	 * The key to retrieve the OMBd API <code>imdbID</code> field.
	 */
	public static final String OMDB_ID_FIELD_KEY = "omdbapi.field.imdbID";

	/**
	 * The key to retrieve the OMBd API <code>Title</code> field.
	 */
	public static final String OMDB_TITLE_FIELD_KEY = "omdbapi.field.Title";

	/**
	 * The key to retrieve the OMBd API <code>imdbRating</code> field.
	 */	
	public static final String OMDB_RATING_FIELD_KEY = "omdbapi.field.imdbRating";

	/**
	 * The key to retrieve the OMBd API <code>Response</code> field (for testing purposes).
	 */	
	public static final String OMDB_RESPONSE_FIELD_KEY = "omdbapi.field.Response";

	/**
	 * The value of the OMDb API <code>Response</code> field in case of movie found.
	 */	
	public static final String OMDB_RESPONSE_FOUND_KEY = "omdbapi.values.true";
	
	/**
	 * Utility method to retrieve a property for the OMDb API from the configuration file.
	 * 
	 * @param key The property key to retrieve
	 * 
	 * @return The correspondant value for the property
	 * 
	 * @throws WsClientConfigException in case of any problem during properties reading.
	 */
	public static String getOMDbApiProperty(String key) throws WsClientConfigException
	{
		// Initialize only once
		if (omdbApiConfigFile == null)
		{
			omdbApiConfigFile = new Properties();
			initOMDbApiConfigFile();
		}
		
		String value = omdbApiConfigFile.getProperty(key);
		// TODO trace property
		
		return value;
	}
	
	/**
	 * Init subrutine for the OMDb API configuration file.
	 * 
	 * @throws WsClientException in case of any initialization issues.
	 */
	private static void initOMDbApiConfigFile() throws WsClientConfigException
	{
		// TODO trace initialization
		InputStream is = OMDbApiConfig.class.getClassLoader().getResourceAsStream(OMDB_CONFIG_FILENAME);
		
		if (is != null)
		{
			try
			{
				// TODO trace loading properties
				omdbApiConfigFile.load(is);
			}
			catch (IOException ioe)
			{
				// TODO trace error
				throw new WsClientConfigException(ioe);
			}
		}
		else
		{
			// TODO trace error
			throw new WsClientConfigException(
					new FileNotFoundException(
							getMessageFromBundle(FILE_NOT_FOUND_ERROR_MSG_KEY, OMDB_CONFIG_FILENAME)));
		}
	}
}
