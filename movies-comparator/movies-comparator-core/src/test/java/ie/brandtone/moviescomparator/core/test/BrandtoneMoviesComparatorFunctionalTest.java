package ie.brandtone.moviescomparator.core.test;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_TITLE_LITERAL_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getMatchingValuesErrorMsg;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ie.brandtone.moviescomparator.core.MovieComparatorService;
import ie.brandtone.moviescomparator.core.impl.MovieComparatorServiceImpl;
import ie.brandtone.moviescomparator.dao.Movie;
import ie.brandtone.moviescomparator.wsclient.MovieRetrieverService;
import ie.brandtone.moviescomparator.wsclient.impl.OMDbApiRestClient;

/**
 * Class test to check the <b>Brandtone Movies Comparator</b> functional requirements.
 * 
 * @author Marco Pala
 * 
 * @version 1.0.0
 */
public class BrandtoneMoviesComparatorFunctionalTest extends BaseCoreModuleTest
{
    /**
     * The {@link MovieRetrieverService} <i>Singleton</i> instance.
     */
    private static MovieRetrieverService movieRetriever;
    
    /**
     * The {@link MovieComparatorService} <i>Singleton</i> instance.
     */
    private static MovieComparatorService movieComparator;
    
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
     * Test the <i>Brandtone Movies Comparator</i> functional requirement no.1: <b>Search for two movies from a user input</b>
     * (a user can input movie titles and get a comparison of their scores as a result. The “winner”, a movie with better score should be highlighted).
     * 
     * @throws Exception in case of any failure
     * 
     * @since v1.0.0
     */
    @Test
    public void functionalRequirementTest001() throws Exception
    {
        String lowerRatedMovieTitle = getTestProperty("core.test.functional001.title1");
        String higherRatedMovieTitle = getTestProperty("core.test.functional001.title2");
                        
        // Compare movies rating
        Movie bestOne = movieComparator.compareMoviesByRating(lowerRatedMovieTitle, higherRatedMovieTitle);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), higherRatedMovieTitle, bestOne.getTitle());
    }
    
    /**
     * Test the <i>Brandtone Movies Comparator</i> functional requirement no.2: <b>Modify local results</b>
     * (a user can change the score of a movie which will be used to display future requests for that particular movie).
     * 
     * @throws Exception in case of any failure
     * 
     * @since v1.0.0
     */
    @Test
    public void functionalRequirementTest002() throws Exception
    {
        String lowerRatedMovieTitle = getTestProperty("core.test.functional002.title1");
        String higherRatedMovieTitle = getTestProperty("core.test.functional002.title2");
                
        // Retrieve the movie to modify
        Movie lowerRatedMovie = movieRetriever.getMovieByTitle(lowerRatedMovieTitle);
        Movie higherRatedMovie = movieRetriever.getMovieByTitle(higherRatedMovieTitle);
        
        // First comparison: no modification to score yet, higher rated movie wins
        Movie bestOne = movieComparator.compareMoviesByRating(lowerRatedMovieTitle, higherRatedMovieTitle);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), higherRatedMovieTitle, bestOne.getTitle());
        
        // Modify local results by increasing lower reated movie score
        lowerRatedMovie.setRating(10.0f);
        
        // Second comparison: lower rated movie (but with the new score) wins
        bestOne = movieComparator.compareMoviesByRating(lowerRatedMovie, higherRatedMovie);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), lowerRatedMovieTitle, bestOne.getTitle());
    }
    
    /**
     * Test the <i>Brandtone Movies Comparator</i> functional requirement no.3: <b>Pre-cook the results</b>
     * (modify the retrieval of the results in such a way that your favourite movie will always receive the higher score when compared against any other movie).
     * 
     * @throws Exception in case of any failure
     * 
     * @since v1.0.0
     */
    @Test
    public void functionalRequirementTest003() throws Exception
    {
        String lowerRatedMovieTitle = getTestProperty("core.test.functional003.title1");
        String higherRatedMovieTitle = getTestProperty("core.test.functional003.title2");
        
        // Retrieve the two given movies by title
        Movie lowerRatedMovie = movieRetriever.getMovieByTitle(lowerRatedMovieTitle);
        Movie higherRatedMovie = movieRetriever.getMovieByTitle(higherRatedMovieTitle);
                
        // First comparison: no favourite yet, higher rated movie wins
        Movie bestOne = movieComparator.compareMoviesByRating(lowerRatedMovie, higherRatedMovie);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), higherRatedMovieTitle, bestOne.getTitle());

        // Mark lower rated one as favourite
        lowerRatedMovie.markAsFavourite();
        // Second comparison: favourite movie wins (even if lower rated)
        bestOne = movieComparator.compareMoviesByRating(lowerRatedMovie, higherRatedMovie);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), lowerRatedMovieTitle, bestOne.getTitle());
    }
}
