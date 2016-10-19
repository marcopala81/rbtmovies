package ie.brandtone.moviescomparator.core.test;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_TITLE_LITERAL_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getMatchingValuesErrorMsg;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ie.brandtone.moviescomparator.dao.entity.MovieEntity;

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
        MovieEntity lowerRatedMovie = movieRetriever.getMovieByTitle(lowerRatedMovieTitle);
        MovieEntity higherRatedMovie = movieRetriever.getMovieByTitle(higherRatedMovieTitle);
        
        MovieEntity bestOne = movieComparator.compareMoviesByRating(lowerRatedMovie, higherRatedMovie);
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
        MovieEntity lowerRatedMovie = movieRetriever.getMovieByTitle(lowerRatedMovieTitle);
        MovieEntity higherRatedMovie = movieRetriever.getMovieByTitle(higherRatedMovieTitle);
        
        // First comparison: no modification to score yet, higher rated movie wins
        MovieEntity bestOne = movieComparator.compareMoviesByRating(lowerRatedMovie, higherRatedMovie);
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
        MovieEntity lowerRatedMovie = movieRetriever.getMovieByTitle(lowerRatedMovieTitle);
        MovieEntity higherRatedMovie = movieRetriever.getMovieByTitle(higherRatedMovieTitle);
                
        // First comparison: no favourite yet, higher rated movie wins
        MovieEntity bestOne = movieComparator.compareMoviesByRating(lowerRatedMovie, higherRatedMovie);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), higherRatedMovieTitle, bestOne.getTitle());

        // Mark lower rated one as favourite
        lowerRatedMovie.setFavourite(true);
        // Second comparison: favourite movie wins (even if lower rated)
        bestOne = movieComparator.compareMoviesByRating(lowerRatedMovie, higherRatedMovie);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), lowerRatedMovieTitle, bestOne.getTitle());
    }
}
