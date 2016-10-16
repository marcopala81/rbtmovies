package ie.brandtone.moviescomparator.utils;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.TEST_CONFIG_ERROR_MSG_KEY;

/**
 * Used for reporting an exception related to the test configuration.
 * 
 * @author Marco Pala
 */
public class TestConfigException extends ConfigException
{
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -3668708644825773176L;

    /**
     * Create a test configuration exception with a native cause.
     * 
     * @param cause The native cause to encapsulate
     */
    public TestConfigException(Throwable cause)
    {
        super(cause, TEST_CONFIG_ERROR_MSG_KEY);
    }
}