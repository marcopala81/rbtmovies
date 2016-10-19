package ie.brandtone.moviescomparator.core.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import ie.brandtone.moviescomparator.core.MovieComparatorService;
import ie.brandtone.moviescomparator.utils.BaseMoviesComparatorTest;
import ie.brandtone.moviescomparator.wsclient.MovieRetrieverService;

/**
 * Base abstract class for the Core Module project's test classes.
 * 
 * @author Marco Pala
 * 
 * @version 1.0.0
 */
@ContextConfiguration(locations = "classpath:core-test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public abstract class BaseCoreModuleTest extends BaseMoviesComparatorTest
{
    /**
     * Configuration filename for the Core Module project's test classes.
     */
    private static final String CORE_TEST_CONFIG_FILENAME = "core.test.properties";

    /**
     * The {@link MovieRetrieverService} <i>Singleton</i> instance.
     */
    @Autowired
    protected MovieRetrieverService movieRetriever;
    
    /**
     * The {@link MovieComparatorService} <i>Singleton</i> instance.
     */
    @Autowired
    protected MovieComparatorService movieComparator;
        
    /**
     * {@inheritDoc}
     */
    @Override
    protected void initConfigFilename()
    {
        setConfigFilename(CORE_TEST_CONFIG_FILENAME);
    }
}
