package ie.brandtone.moviescomparator.dao.test;

import ie.brandtone.moviescomparator.utils.BaseMoviesComparatorTest;

/**
 * Base abstract class for the DAO Module project's test classes.
 * 
 * @author Marco Pala
 * 
 * @version 1.0.0
 */
public abstract class BaseDAOModuleTest extends BaseMoviesComparatorTest
{
    /**
     * Configuration filename for the DAO Module project's test classes.
     */
    private static final String DAO_TEST_CONFIG_FILENAME = "dao.test.properties";
        
    /**
     * {@inheritDoc}
     */
    @Override
    protected void initConfigFilename()
    {
        setConfigFilename(DAO_TEST_CONFIG_FILENAME);
    }
}
