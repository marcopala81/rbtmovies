package ie.brandtone.moviescomparator.core.test;

import static ie.brandtone.moviescomparator.utils.Commons.getMatchingValuesErrorMsg;
import static ie.brandtone.moviescomparator.utils.Constants.MOVIE_TITLE_LITERAL_KEY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import ie.brandtone.moviescomparator.core.MovieComparatorService;
import ie.brandtone.moviescomparator.core.exception.MovieComparatorException;
import ie.brandtone.moviescomparator.core.impl.MovieComparatorServiceImpl;
import ie.brandtone.moviescomparator.dao.Movie;

/**
 * Class test for the {@link MovieComparatorService} interface.
 * 
 * @author Marco Pala
 */
public class MovieComparatorServiceTest
{
	// TODO Move to Constants
	private static final String TEST_POOR_TITLE = "Soultaker";
	private static final String TEST_GOOD_TITLE = "Terminator";
	
	/**
	 * Test the {@link MovieComparatorService#compareMoviesByRating(String, String)} method with two known titles (the first one is better).
	 * 
	 * @throws MovieComparatorException in case of any failure
	 */
	@Test
	public void compareMoviesByRatingTest001() throws MovieComparatorException
	{
		Movie bestOne = compareMoviesByRatingCommonTest(TEST_GOOD_TITLE, TEST_POOR_TITLE);
		assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), TEST_GOOD_TITLE, bestOne.getTitle());
	}
	
	/**
	 * Test the {@link MovieComparatorService#compareMoviesByRating(String, String)} method with two known titles (the second one is better).
	 * 
	 * @throws MovieComparatorException in case of any failure
	 */
	@Test
	public void getMovieByTitleTest002() throws MovieComparatorException
	{
		Movie bestOne = compareMoviesByRatingCommonTest(TEST_POOR_TITLE, TEST_GOOD_TITLE);
		assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), TEST_GOOD_TITLE, bestOne.getTitle());
	}
	
	/**
	 * Test the {@link MovieComparatorService#compareMoviesByRating(String, String)} method checking the same title (<code>null</code> expected).
	 * 
	 * @throws MovieComparatorException in case of any failure
	 */
	@Test
	public void getMovieByTitleTest003() throws MovieComparatorException
	{
		Movie sameOne = compareMoviesByRatingCommonTest(TEST_GOOD_TITLE, TEST_GOOD_TITLE);
		assertNull(sameOne);
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
