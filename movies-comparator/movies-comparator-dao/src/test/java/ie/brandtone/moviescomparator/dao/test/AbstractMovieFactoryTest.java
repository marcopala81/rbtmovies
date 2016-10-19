package ie.brandtone.moviescomparator.dao.test;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_TITLE_LITERAL_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getMatchingValuesErrorMsg;
import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

import ie.brandtone.moviescomparator.dao.AbstractMovieFactory;
import ie.brandtone.moviescomparator.dao.entity.MovieEntity;
import ie.brandtone.moviescomparator.utils.BaseMoviesComparatorTest;

/**
 * Class test for the {@link AbstractMovieFactory} object.
 * 
 * @author Marco Pala
 */
public class AbstractMovieFactoryTest extends BaseDAOModuleTest
{   
    /**
     * The expected value for the movie2 title.
     */
    private static final String MOVIE_2_TITLE_EXPECTED = "The Matrix";

    /**
     * Test the {@link AbstractMovieFactory#newMovieEntity(String, String, String)} constructor.
     * 
     * @throws Exception in case of any failure
     */
    @Test
    public void newMovieEntityTest001() throws Exception
    {
        String id = getTestProperty("dao.test.movie1.id");
        String title = getTestProperty("dao.test.movie1.title");
        String rating = getTestProperty("dao.test.movie1.rating");
        
        MovieEntity testMovie = AbstractMovieFactory.newMovieEntity(id, title, rating);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), title, testMovie.getTitle());
    }
        
    /**
     * Test the {@link AbstractMovieFactory#getMovieFromJson(JSONObject)} constructor.
     * 
     * @throws Exception in case of any failure
     */
    @Test
    public void getMovieFromJsonTest002() throws Exception
    {
        String jsonStringMovie = getTestProperty("dao.test.movie2.json.string");
        JSONObject jsonMovie = new JSONObject(jsonStringMovie);
        
        MovieEntity testMovie = AbstractMovieFactory.getMovieFromJson(jsonMovie);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), MOVIE_2_TITLE_EXPECTED, testMovie.getTitle());
    }
    
    /**
     * Test the {@link AbstractMovieFactory#getMovieFromJson(String)} constructor.
     * 
     * @throws Exception in case of any failure
     */
    @Test
    public void getMovieFromJsonStringTest003() throws Exception
    {
        String jsonStringMovie = getTestProperty("dao.test.movie2.json.string");
        
        MovieEntity testMovie = AbstractMovieFactory.getMovieFromJson(jsonStringMovie);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), MOVIE_2_TITLE_EXPECTED, testMovie.getTitle());
    }
    
    /**
     * Test the {@link AbstractMovieFactory#getMovieFromTestConfig(BaseMoviesComparatorTest, String, String, String)} constructor.
     * 
     * @throws Exception in case of any failure
     */
    @Test
    public void getMovieFromTestConfigTest004() throws Exception
    {
        String idKey = getTestProperty("dao.test.key.id");
        String titleKey = getTestProperty("dao.test.key.title");
        String ratingKey = getTestProperty("dao.test.key.rating");
        
        MovieEntity testMovie = AbstractMovieFactory.getMovieFromTestConfig(this, idKey, titleKey, ratingKey);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), getTestProperty("dao.test.movie1.title"), testMovie.getTitle());
    }
}
