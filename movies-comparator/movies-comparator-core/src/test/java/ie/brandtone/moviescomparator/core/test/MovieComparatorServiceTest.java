package ie.brandtone.moviescomparator.core.test;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_TITLE_LITERAL_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getMatchingValuesErrorMsg;
import static ie.brandtone.moviescomparator.dao.AbstractMovieFactory.getMovieFromJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import ie.brandtone.moviescomparator.core.MovieComparatorService;
import ie.brandtone.moviescomparator.dao.Movie;

/**
 * Class test for the {@link MovieComparatorService} interface.
 * 
 * @author Marco Pala
 */
public class MovieComparatorServiceTest extends BaseCoreModuleTest
{    
    /**
     * Test the {@link MovieComparatorService#compareMoviesByRating(String, String)} method with two known titles (the first one is better).
     * 
     * @throws Exception in case of any failure
     */
    @Test
    public void compareMovieTitlesByRatingTest001() throws Exception
    {
        String betterMovieTitle = getTestProperty("core.test.betterMovie001");
        String worseMovieTitle = getTestProperty("core.test.worseMovie001");

        Movie bestOne = movieComparator.compareMoviesByRating(betterMovieTitle, worseMovieTitle);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), betterMovieTitle, bestOne.getTitle());
    }

    /**
     * Test the {@link MovieComparatorService#compareMoviesByRating(String, String)} method with two known titles (the second one is better).
     * 
     * @throws Exception in case of any failure
     */
    @Test
    public void compareMovieTitlesByRatingTest002() throws Exception
    {
        String worseMovieTitle = getTestProperty("core.test.worseMovie002");
        String betterMovieTitle = getTestProperty("core.test.betterMovie002");

        Movie bestOne = movieComparator.compareMoviesByRating(worseMovieTitle, betterMovieTitle);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), betterMovieTitle, bestOne.getTitle());
    }

    /**
     * Test the {@link MovieComparatorService#compareMoviesByRating(String, String)} method checking the same rating scenario (<code>null</code> expected).
     * 
     * @throws Exception in case of any failure
     */
    @Test
    public void compareMovieTitlesByRatingTest003() throws Exception
    {
        String sameRatingMovie = getTestProperty("core.test.sameRatingMovie003");

        Movie sameOne = movieComparator.compareMoviesByRating(sameRatingMovie, sameRatingMovie);
        assertNull(sameOne);
    }

    /**
     * Test the {@link MovieComparatorService#compareMoviesByRating(Movie, Movie)} method with two known movies (the first one is better).
     * 
     * @throws Exception in case of any failure
     */
    @Test
    public void compareMoviesByRatingTest004() throws Exception
    {
        Movie betterMovie = getMovieFromJson(getTestProperty("core.test.betterMovie004"));
        Movie worseMovie = getMovieFromJson(getTestProperty("core.test.worseMovie004"));
        
        Movie bestOne = movieComparator.compareMoviesByRating(betterMovie, worseMovie);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), betterMovie.getTitle(), bestOne.getTitle());
    }   
}
