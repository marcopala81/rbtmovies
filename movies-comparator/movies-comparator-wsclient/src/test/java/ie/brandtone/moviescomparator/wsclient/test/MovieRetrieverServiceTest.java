package ie.brandtone.moviescomparator.wsclient.test;

import static ie.brandtone.moviescomparator.utils.Commons.getMatchingValuesErrorMsg;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_TITLE_LITERAL_KEY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ie.brandtone.moviescomparator.dao.Movie;
import ie.brandtone.moviescomparator.dao.exception.BadMovieFormatException;
import ie.brandtone.moviescomparator.utils.BaseMoviesComparatorTest;
import ie.brandtone.moviescomparator.wsclient.exception.MovieNotFoundException;
import ie.brandtone.moviescomparator.wsclient.MovieRetrieverService;
import ie.brandtone.moviescomparator.wsclient.exception.WsClientException;
import ie.brandtone.moviescomparator.wsclient.impl.OMDbApiRestClient;

/**
 * Class test for the {@link MovieRetrieverService} interface.
 * 
 * @author Marco Pala
 */
public class MovieRetrieverServiceTest extends BaseMoviesComparatorTest
{
    /**
     * Configuration filename for this test class.
     */
    private static final String WSCLIENT_TEST_CONFIG_FILENAME = "wsclient.test.properties";

    /**
     * Test the {@link MovieRetrieverService#getMovieByTitle(String)} method with a matching title.
     * 
     * @throws Exception in case of any failure ({@link WsClientException} | {@link BadMovieFormatException} | {@link MovieNotFoundException})
     */
    @Test
    public void getMovieByTitleTest001() throws Exception
    {
        String movieFoundTitle = getTestProperty("wsclient.test.movieFound");

        Movie movie = getMovieByTitleCommonTest(movieFoundTitle);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), movieFoundTitle, movie.getTitle());
    }

    /**
     * Test the {@link MovieRetrieverService#getMovieByTitle(String)} method checking an unexpected title.
     * 
     * @throws Exception in case of any failure ({@link WsClientException} | {@link BadMovieFormatException} | {@link MovieNotFoundException})
     */
    @Test(expected = MovieNotFoundException.class)
    public void getMovieByTitleTest002() throws Exception
    {
        String unmatchingTitle = getTestProperty("wsclient.test.unmatchingTitle");
        getMovieByTitleCommonTest(unmatchingTitle);
    }

    /**
     * Test the {@link MovieRetrieverService#getMovieByTitle(String)} method checking a not found title.
     * 
     * @throws Exception in case of any failure ({@link WsClientException} | {@link BadMovieFormatException} | {@link MovieNotFoundException})
     */
    @Test(expected = MovieNotFoundException.class)
    public void getMovieByTitleTest003() throws Exception
    {
        String notFoundTitle = getTestProperty("wsclient.test.notFoundTitle");

        MovieRetrieverService mrs = OMDbApiRestClient.getInstance();
        mrs.getMovieByTitle(notFoundTitle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initConfigFilename()
    {
        setConfigFilename(WSCLIENT_TEST_CONFIG_FILENAME);
    }

    /**
     * Utility method common to most of these test cases (OMDbApi client request and basic not-null assertion).
     * 
     * @param testTitle The test title to retrieve
     * 
     * @return The movie object matching the title
     * @throws Exception in case of any failure ({@link WsClientException} | {@link BadMovieFormatException} | {@link MovieNotFoundException})
     */
    private Movie getMovieByTitleCommonTest(String testTitle) throws Exception
    {
        Movie movie = null;
        MovieRetrieverService mrs = OMDbApiRestClient.getInstance();
        movie = mrs.getMovieByTitle(testTitle);
        assertNotNull(movie);

        return movie;
    }
}
