package ie.brandtone.moviescomparator.dao.test;

import static ie.brandtone.moviescomparator.dao.AbstractMovieFactory.newMovie;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_TITLE_LITERAL_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getMatchingValuesErrorMsg;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ie.brandtone.moviescomparator.dao.Movie;
import ie.brandtone.moviescomparator.utils.BaseMoviesComparatorTest;

/**
 * Class test for the {@link Movie} object.
 * 
 * @author Marco Pala
 */
public class MovieTest extends BaseMoviesComparatorTest
{
    /**
     * Configuration filename for this test class.
     */
    private static final String DAO_TEST_CONFIG_FILENAME = "dao.test.properties";
    
    /**
     * Test the {@link Movie} constructor.
     * 
     * @throws Exception in case of any failure
     */
    @Test
    public void movieTest001() throws Exception
    {
        String id = getTestProperty("dao.test.movie1.id");
        String title = getTestProperty("dao.test.movie1.title");
        String rating = getTestProperty("dao.test.movie1.rating");
        
        Movie testMovie = newMovie(id, title, Float.parseFloat(rating));
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), title, testMovie.getTitle());
    }

    /**
     * Test the {@link Movie#markAsFavourite()} method.
     * 
     * @throws Exception in case of any failure
     */
    @Test
    public void markAsFavouriteTest002() throws Exception
    {
        String id = getTestProperty("dao.test.movie2.id");
        String title = getTestProperty("dao.test.movie2.title");
        String rating = getTestProperty("dao.test.movie2.rating");
        
        Movie testMovie = newMovie(id, title, Float.parseFloat(rating));        
        assertFalse(testMovie.isFavourite());
        
        // Mark as favourite
        testMovie.markAsFavourite();
        assertTrue(testMovie.isFavourite());        
    }   
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void initConfigFilename()
    {
        setConfigFilename(DAO_TEST_CONFIG_FILENAME);
    }
}
