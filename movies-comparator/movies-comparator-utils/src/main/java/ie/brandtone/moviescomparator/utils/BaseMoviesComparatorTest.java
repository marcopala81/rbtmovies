package ie.brandtone.moviescomparator.utils;

import org.junit.Rule;
import org.junit.rules.TestRule;

import ie.brandtone.moviescomparator.utils.exceptions.ConfigException;
import ie.brandtone.moviescomparator.utils.exceptions.TestConfigException;

/**
 * Base abstract class for the Movies Comparator project's test classes.
 * 
 * @author Marco Pala
 * 
 * @version 1.0.0
 */
public abstract class BaseMoviesComparatorTest extends AbstractConfigReader
{    
    /**
     * Common JUnit rule to trace the name of the test before execution.
     */
    @Rule
    public TestRule testNameLogger = new TestLogger();
    
    /**
     * Utility method to retrieve a property for the test from the test configuration file.
     * 
     * @param key The property key to retrieve
     * 
     * @return The correspondant value for the property
     * 
     * @throws TestConfigException in case of any problem during properties reading
     */
    public String getTestProperty(String key) throws TestConfigException
    {
        String value = null;
        
        try
        {
            value = getProperty(key); 
        }
        catch (ConfigException ce)
        {
            throw (TestConfigException) ce;
        }
        
        return value;
    }
    
    /**
     * Initialization subrutine for the test subclasses (set up configuration file).
     * 
     * @throws TestConfigException in case of any initialization issues
     */
    @Override
    protected void initConfigFile() throws TestConfigException
    {
        try
        {
            loadProperties(this.getClass(), getConfigFilename());
        }
        catch (ConfigException ce)
        {
            throw (TestConfigException) ce;
        }
    }
}
