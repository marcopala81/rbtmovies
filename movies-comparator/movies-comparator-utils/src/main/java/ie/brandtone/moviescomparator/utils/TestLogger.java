package ie.brandtone.moviescomparator.utils;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.RUNNING_JUNIT_TEST_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;

import org.apache.log4j.Logger;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * {@link TestWatcher} to create a custom JUnit <code>@Rule</code> to trace test methods' names.
 * 
 * @author Marco Pala
 */
public final class TestLogger extends TestWatcher
{
    /**
     * The Apache Log4j logger.
     */
    private static final Logger LOGGER = Logger.getLogger(TestLogger.class);
    
    /**
     * Trace a JUnit test method's name on start.
     * 
     * @param description The JUnit test description
     */
    @Override
    protected void starting(Description description)
    {
        LOGGER.info(getMessageFromBundle(RUNNING_JUNIT_TEST_MSG_KEY, description.getMethodName()));
    }
}
