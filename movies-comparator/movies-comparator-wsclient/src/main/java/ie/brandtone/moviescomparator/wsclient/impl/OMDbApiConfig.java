package ie.brandtone.moviescomparator.wsclient.impl;

import static ie.brandtone.moviescomparator.utils.Commons.getPropertiesInitMsg;
import static ie.brandtone.moviescomparator.utils.Commons.getPropertyReadingMsg;
import static ie.brandtone.moviescomparator.utils.Commons.loadProperties;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import ie.brandtone.moviescomparator.wsclient.exception.WsClientConfigException;

/**
 * This class holds all the relevant literals and constants of the OMDb API webservice configuration.
 * 
 * @author Marco Pala
 */
public final class OMDbApiConfig
{
    /**
     * The Apache Log4j logger.
     */
    private static final Logger LOGGER = Logger.getLogger(OMDbApiConfig.class);

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
     * @throws WsClientConfigException in case of any problem during properties reading
     */
    public static String getOMDbApiProperty(String key) throws WsClientConfigException
    {
        // Initialize only once
        if (omdbApiConfigFile == null)
        {
            omdbApiConfigFile = new Properties();
            try
            {
                LOGGER.debug(getPropertiesInitMsg(OMDB_CONFIG_FILENAME));
                initOMDbApiConfigFile();
            }
            catch (WsClientConfigException wscce)
            {
                LOGGER.error(wscce);
                throw (WsClientConfigException) wscce;
            }
        }

        String value = omdbApiConfigFile.getProperty(key);
        LOGGER.debug(getPropertyReadingMsg(key, value));

        return value;
    }

    /**
     * Init subrutine for the OMDb API configuration file.
     * 
     * @throws WsClientConfigException in case of any initialization issues.
     */
    private static void initOMDbApiConfigFile() throws WsClientConfigException
    {
        try
        {
            omdbApiConfigFile = loadProperties(omdbApiConfigFile, OMDbApiConfig.class, OMDB_CONFIG_FILENAME);
        }
        catch (IOException ioe)
        {
            throw new WsClientConfigException(ioe);
        }
    }
    
    /**
     * Private constructor (for Checkstyle violations purposes).
     */
    private OMDbApiConfig()
    {
        // EMPTY BLOCK
    }
}
