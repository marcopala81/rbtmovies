package ie.brandtone.moviescomparator.wsclient.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import static ie.brandtone.moviescomparator.wsclient.Constants.MATCHING_VALUES_ERROR_MSG;
import static ie.brandtone.moviescomparator.wsclient.Constants.MATCHING_EXCEPTION_TYPE_ERROR_MSG;
import static ie.brandtone.moviescomparator.wsclient.Constants.MOVIE_TITLE;

import org.junit.Test;

import ie.brandtone.moviescomparator.wsclient.BadMovieFormatException;
import ie.brandtone.moviescomparator.wsclient.Movie;
import ie.brandtone.moviescomparator.wsclient.MovieNotFoundException;
import ie.brandtone.moviescomparator.wsclient.MovieRetrieverService;
import ie.brandtone.moviescomparator.wsclient.OMDbApiRestClient;
import ie.brandtone.moviescomparator.wsclient.WsClientException;

/**
 * Class test for the {@link MovieRetrieverService} interface.
 * 
 * @author Marco Pala
 */
public class MovieRetrieverServiceTest
{
	private static final String TEST_FOUND_TITLE = "Titanic";
	private static final String TEST_UNMATCHING_TITLE = "Tit";
	private static final String TEST_NOT_FOUND_TITLE = "Marcomarcomarco";
	
	/**
	 * Test the {@link MovieRetrieverService#getMovieByTitle(String)} method with a matching title.
	 */
	@Test
	public void getMovieByTitleTest001()
	{
		Movie movie = getMovieByTitleCommonTest(TEST_FOUND_TITLE);
		assertEquals(String.format(MATCHING_VALUES_ERROR_MSG, MOVIE_TITLE), TEST_FOUND_TITLE, movie.getTitle());
	}
	
	/**
	 * Test the {@link MovieRetrieverService#getMovieByTitle(String)} method checking an unexpected title.
	 */
	@Test
	public void getMovieByTitleTest002()
	{
		Movie movie = getMovieByTitleCommonTest(TEST_UNMATCHING_TITLE);
		assertNotEquals(String.format(MATCHING_VALUES_ERROR_MSG, MOVIE_TITLE), TEST_UNMATCHING_TITLE, movie.getTitle());
	}
	
	/**
	 * Test the {@link MovieRetrieverService#getMovieByTitle(String)} method checking a not found title.
	 */
	@Test
	public void getMovieByTitleTest003()
	{
		try
		{
			MovieRetrieverService mrs = OMDbApiRestClient.getInstance();
			mrs.getMovieByTitle(TEST_NOT_FOUND_TITLE);
		}
		catch (Exception e)
		{
			assertEquals(MATCHING_EXCEPTION_TYPE_ERROR_MSG, MovieNotFoundException.class.getSimpleName(), e.getClass().getSimpleName());
		}		
	}

	/**
	 * Utility method common to most of these test cases (OMDbApi client request and basic not-null assertion).
	 * 
	 * @param testTitle The test title to retrieve
	 * @return The movie object matching the title
	 */
	private Movie getMovieByTitleCommonTest(String testTitle)
	{
		Movie movie = null;
		
		try
		{
			MovieRetrieverService mrs = OMDbApiRestClient.getInstance();
			movie = mrs.getMovieByTitle(testTitle);
		}
		catch (WsClientException|MovieNotFoundException|BadMovieFormatException e)
		{
			fail(e.toString());
		}
		
		assertNotNull(movie);
		
		return movie;
	}	
}
