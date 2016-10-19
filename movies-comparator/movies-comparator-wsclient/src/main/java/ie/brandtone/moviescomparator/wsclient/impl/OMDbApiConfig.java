package ie.brandtone.moviescomparator.wsclient.impl;

import ie.brandtone.moviescomparator.utils.AbstractConfigReader;
import ie.brandtone.moviescomparator.utils.exceptions.ConfigException;
import ie.brandtone.moviescomparator.wsclient.exception.WsClientConfigException;

/**
 * This class holds all the relevant literals and constants of the OMDb API webservice configuration.
 * 
 * @author Marco Pala
 */
public final class OMDbApiConfig extends AbstractConfigReader
{    
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
    public String getOMDbApiProperty(String key) throws WsClientConfigException
    {
        String value = null;
        
        try
        {
            value = getProperty(key); 
        }
        catch (ConfigException ce)
        {
            throw (WsClientConfigException) ce;
        }
        
        return value;
    }
    
    /**
     * Init subrutine for the OMDb API configuration file.
     * 
     * @throws WsClientConfigException in case of any initialization issues.
     */
    @Override
    protected void initConfigFile() throws WsClientConfigException
    {
        try
        {
            loadProperties(this.getClass(), getConfigFilename());
        }
        catch (ConfigException ce)
        {
            throw (WsClientConfigException) ce;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initConfigFilename()
    {
        setConfigFilename(OMDB_CONFIG_FILENAME);
    }
    
    /**
     * Private constructor (for Checkstyle violations purposes).
     */
    OMDbApiConfig()
    {
        // EMPTY BLOCK
    }
}
