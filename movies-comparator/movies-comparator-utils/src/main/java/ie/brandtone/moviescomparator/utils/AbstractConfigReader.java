package ie.brandtone.moviescomparator.utils;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.FILE_NOT_FOUND_ERROR_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.PROPERTIES_INIT_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.PROPERTY_READING_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;
import static ie.brandtone.moviescomparator.utils.Commons.isNullString;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Base class for reading configuration files.
 * 
 * @author Marco Pala
 * 
 * @version 1.0.0
 */
public abstract class AbstractConfigReader
{
    /**
     * The {@link Properties} configuration file.
     */
    private Properties configFile;

    /**
     * The configuration file name.
     */
    private String configFilename;
    
    /**
     * The {@link AbstractConfigReader} Apache Log4j logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AbstractConfigReader.class);
    
    /**
     * Mandatory method to implement to initialize the configuration file (see {@link AbstractConfigReader#loadProperties(Properties, Class, String)}).
     * 
     * @throws ConfigException in case of any initialization issues.
     * 
     * @since v1.0.0
     */
    protected abstract void initConfigFile() throws ConfigException;

    /**
     * Mandatory method to implement to set the configuration filename.
     * 
     * @since v1.0.0
     */
    protected abstract void initConfigFilename();
        
    /**
     * Utility method to retrieve a property from the configuration file.
     * 
     * @param key The property key to retrieve
     * 
     * @return The correspondant value for the property
     * 
     * @throws ConfigException in case of any problem during properties reading
     */
    protected String getProperty(String key) throws ConfigException
    {
        // Initialize only once
        if (configFile == null)
        {
            configFile = new Properties();
            initConfigFile();
        }

        String value = configFile.getProperty(key);
        LOGGER.debug(getMessageFromBundle(PROPERTY_READING_MSG_KEY, key, value));

        return value;
    }
        
    /**
     * Get the configuration file name.
     *
     * @return The configuration file name
     */
    protected String getConfigFilename()
    {
        if (isNullString(configFilename))
        {
            initConfigFilename();
        }

        return configFilename;
    }
    
    /**
     * Set the configuration file name.
     *
     * @param configFilenameIn The configuration file name to set
     */
    protected void setConfigFilename(String configFilenameIn)
    {
        configFilename = configFilenameIn;
    }
        
    /**
     * Common subroutine to load a {@link Properties} file.
     * 
     * @param clazz The subclass invoking this method
     * @param configFilenameIn The Properties filename
     * 
     * @throws ConfigException in case of any I/O issue
     */
    protected void loadProperties(Class<?> clazz, String configFilenameIn) throws ConfigException
    {
        configFilename = configFilenameIn;
        LOGGER.debug(getMessageFromBundle(PROPERTIES_INIT_MSG_KEY, configFilenameIn));
        
        InputStream is = clazz.getClassLoader().getResourceAsStream(configFilename);

        if (is != null)
        {
            try
            {
                configFile.load(is);
                is.close();                
            }
            catch (IOException ioe)
            {
                LOGGER.error(ioe);
                throw new ConfigException(ioe);
            }
        }
        else
        {
            FileNotFoundException fnfe = new FileNotFoundException(getMessageFromBundle(FILE_NOT_FOUND_ERROR_MSG_KEY, configFilenameIn)); 
            LOGGER.error(fnfe);
            throw new ConfigException(fnfe);
        }
    }
}
