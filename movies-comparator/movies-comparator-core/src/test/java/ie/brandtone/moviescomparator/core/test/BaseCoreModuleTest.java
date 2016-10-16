package ie.brandtone.moviescomparator.core.test;

import org.junit.BeforeClass;

import ie.brandtone.moviescomparator.core.MovieComparatorService;
import ie.brandtone.moviescomparator.core.impl.MovieComparatorServiceImpl;
import ie.brandtone.moviescomparator.utils.BaseMoviesComparatorTest;
import ie.brandtone.moviescomparator.wsclient.MovieRetrieverService;
import ie.brandtone.moviescomparator.wsclient.impl.OMDbApiRestClient;

/**
 * Base abstract class for the Core Module project's test classes.
 * 
 * @author Marco Pala
 * 
 * @version 1.0.0
 */
public abstract class BaseCoreModuleTest extends BaseMoviesComparatorTest
{
    /**
     * Configuration filename for the Core Module project's test classes.
     */
    private static final String CORE_TEST_CONFIG_FILENAME = "core.test.properties";

    /**
     * The {@link MovieRetrieverService} <i>Singleton</i> instance.
     */
    protected static MovieRetrieverService movieRetriever;
    
    /**
     * The {@link MovieComparatorService} <i>Singleton</i> instance.
     */
    protected static MovieComparatorService movieComparator;
    
    /**
     * Set up the functional test class (initialize both {@link MovieRetrieverService} and {@link MovieComparatorService}).
     * 
     * @throws Exception in case of any initialization issue.
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        movieRetriever = OMDbApiRestClient.getInstance();
        movieComparator = MovieComparatorServiceImpl.getInstance();
    }
        
    /**
     * {@inheritDoc}
     */
    @Override
    protected void initConfigFilename()
    {
        setConfigFilename(CORE_TEST_CONFIG_FILENAME);
    }
}
