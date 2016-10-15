package ie.brandtone.moviescomparator.core.test;

import static ie.brandtone.moviescomparator.utils.Commons.getMatchingValuesErrorMsg;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_TITLE_LITERAL_KEY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import ie.brandtone.moviescomparator.core.MovieComparatorService;
import ie.brandtone.moviescomparator.core.exception.MovieComparatorException;
import ie.brandtone.moviescomparator.core.impl.MovieComparatorServiceImpl;
import ie.brandtone.moviescomparator.dao.Movie;
import ie.brandtone.moviescomparator.utils.BaseMoviesComparatorTest;

/**
 * Class test for the {@link MovieComparatorService} interface.
 * 
 * @author Marco Pala
 */
public class MovieComparatorServiceTest extends BaseMoviesComparatorTest
{
    /**
     * Configuration filename for this test class.
     */
    private static final String TEST_CONFIG_FILENAME = "core.test.properties";

    /**
     * Test the {@link MovieComparatorService#compareMoviesByRating(String, String)} method with two known titles (the first one is better).
     * 
     * @throws Exception in case of any failure
     */
    @Test
    public void compareMoviesByRatingTest001() throws Exception
    {
        String betterMovie = getTestProperty("core.test.betterMovie001");
        String worseMovie = getTestProperty("core.test.worseMovie001");

        Movie bestOne = compareMoviesByRatingCommonTest(betterMovie, worseMovie);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), betterMovie, bestOne.getTitle());
    }

    /**
     * Test the {@link MovieComparatorService#compareMoviesByRating(String, String)} method with two known titles (the second one is better).
     * 
     * @throws Exception in case of any failure
     */
    @Test
    public void compareMoviesByRatingTest002() throws Exception
    {
        String worseMovie = getTestProperty("core.test.worseMovie002");
        String betterMovie = getTestProperty("core.test.betterMovie002");

        Movie bestOne = compareMoviesByRatingCommonTest(worseMovie, betterMovie);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), betterMovie, bestOne.getTitle());
    }

    /**
     * Test the {@link MovieComparatorService#compareMoviesByRating(String, String)} method checking the same rating scenario (<code>null</code> expected).
     * 
     * @throws Exception in case of any failure
     */
    @Test
    public void compareMoviesByRatingTest003() throws Exception
    {
        String sameRatingMovie = getTestProperty("core.test.sameRatingMovie");

        Movie sameOne = compareMoviesByRatingCommonTest(sameRatingMovie, sameRatingMovie);
        assertNull(sameOne);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initConfigFilename()
    {
        setConfigFilename(TEST_CONFIG_FILENAME);
    }

    /**
     * Utility method common to most of these test cases (MovieComparatorService initialization).
     * 
     * @param testTitle1 The first test title to compare
     * @param testTitle2 The second test title to compare
     * 
     * @return The best movie object (or <code>null</code> if they have the same rating)
     * @throws MovieComparatorException in case of any failure
     */
    private Movie compareMoviesByRatingCommonTest(String testTitle1, String testTitle2) throws MovieComparatorException
    {
        Movie bestOne = null;
        MovieComparatorService mcs = MovieComparatorServiceImpl.getInstance();
        bestOne = mcs.compareMoviesByRating(testTitle1, testTitle2);

        return bestOne;
    }
}
