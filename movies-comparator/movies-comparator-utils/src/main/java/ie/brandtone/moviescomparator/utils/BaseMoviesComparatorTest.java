package ie.brandtone.moviescomparator.utils;

import static ie.brandtone.moviescomparator.utils.Commons.getPropertiesInitMsg;
import static ie.brandtone.moviescomparator.utils.Commons.getPropertyReadingMsg;
import static ie.brandtone.moviescomparator.utils.Commons.loadProperties;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Base abstract class for the test classes of the Movies Comparator project.
 * 
 * @author Marco Pala
 * 
 * @version 1.0.0
 */
public abstract class BaseMoviesComparatorTest
{
    /**
     * The Apache Log4j logger.
     */
    private Logger logger = Logger.getLogger(BaseMoviesComparatorTest.class);

    /**
     * The configuration filename for test classes.
     */
    private String configFilename;

    /**
     * The configuration file for test classes.
     */
    private static Properties testConfigFile;

    /**
     * Mandatory abstract method to implement to set the configuration filename.
     * 
     * @since v1.0.0
     */
    protected abstract void initConfigFilename();

    /**
     * Utility method to retrieve a property for the test from the given configuration file.
     * 
     * @param key The property key to retrieve
     * 
     * @return The correspondant value for the property
     * 
     * @throws TestConfigException in case of any problem during properties reading
     */
    protected String getTestProperty(String key) throws TestConfigException
    {
        // Initialize on first access
        if (getTestConfigFile() == null)
        {
            setTestConfigFile(new Properties());
            initConfigFile();
        }

        String value = getTestConfigFile().getProperty(key);
        logger.debug(getPropertyReadingMsg(key, value));

        return value;
    }

    /**
     * Get the test configuration filename.
     *
     * @return The test configuration filename
     */
    protected String getConfigFilename()
    {
        if (this.configFilename == null || this.configFilename.isEmpty())
        {
            initConfigFilename();
        }

        return configFilename;
    }

    /**
     * Set the test configuration file.
     *
     * @param configFilename The test configuration filename to set
     */
    protected void setConfigFilename(String configFilename)
    {
        this.configFilename = configFilename;
    }

    /**
     * Get the test configuration file.
     *
     * @return The test configuration file
     */
    protected Properties getTestConfigFile()
    {
        return testConfigFile;
    }

    /**
     * Set the test configuration file.
     *
     * @param testConfigFileIn The test configuration file to set
     */
    protected void setTestConfigFile(Properties testConfigFileIn)
    {
        testConfigFile = testConfigFileIn;
    }

    /**
     * Initialization subrutine for the test subclasses (set up configuration file).
     * 
     * @throws TestConfigException in case of any initialization issues
     */
    private void initConfigFile() throws TestConfigException
    {
        try
        {
            logger.debug(getPropertiesInitMsg(getConfigFilename()));
            testConfigFile = loadProperties(testConfigFile, this.getClass(), getConfigFilename());
        }
        catch (IOException ioe)
        {
            logger.error(ioe);
            throw new TestConfigException(ioe);
        }
    }
}
