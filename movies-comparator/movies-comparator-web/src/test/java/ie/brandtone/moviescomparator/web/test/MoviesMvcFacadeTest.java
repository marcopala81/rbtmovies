package ie.brandtone.moviescomparator.web.test;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_TITLE_LITERAL_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_RATING_LITERAL_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.NUM_OF_MOVIES_LITERAL_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getMatchingValuesErrorMsg;
import static ie.brandtone.moviescomparator.dao.AbstractMovieFactory.getMovieFromJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import ie.brandtone.moviescomparator.dao.entity.MovieEntity;
import ie.brandtone.moviescomparator.utils.BaseMoviesComparatorTest;
import ie.brandtone.moviescomparator.web.MoviesMvcFacade;
import ie.brandtone.moviescomparator.web.model.MoviesCompareForm;

/**
 * Class test for the {@link MoviesMvcFacade} interface.
 * 
 */
@ContextConfiguration(locations = "classpath:web-test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class MoviesMvcFacadeTest extends BaseMoviesComparatorTest
{    
    /**
     * Configuration filename for the Web Module project's test classes.
     */
    private static final String WEB_TEST_CONFIG_FILENAME = "web.test.properties";
    
    /**
     * The {@link MoviesMvcFacade} <i>Singleton</i> instance.
     */
    @Autowired
    protected MoviesMvcFacade mvcFacade;
    
    /**
     * Test the {@link MoviesMvcFacade#prepareComparisonResults(MoviesCompareForm)} method with two known titles (the first one is better).
     * 
     * @throws Exception in case of any failure
     */
    @Test
    public void prepareComparisonResultsTest001() throws Exception
    {
        String betterMovieTitle = getTestProperty("web.test.betterMovie001");
        String worseMovieTitle = getTestProperty("web.test.worseMovie001");
        
        MoviesCompareForm model = new MoviesCompareForm();
        model.setMovieTitle1(betterMovieTitle);
        model.setMovieTitle2(worseMovieTitle);
        
        model = mvcFacade.prepareComparisonResults(model);
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), betterMovieTitle.toUpperCase(), model.getWinnerMovie().getTitle());
    }
    
    /**
     * Test the {@link MoviesMvcFacade#checkForLocalModifications(MoviesCompareForm)} method with two known titles.
     * 
     * @throws Exception in case of any failure
     */
    @Test
    public void checkForLocalModificationsTest002() throws Exception
    {
        String movieUnchangedJson1 = getTestProperty("web.test.movie1Unchanged002");
        String movieToUpdateJson2 = getTestProperty("web.test.movie2ToUpdate002");
        
        MoviesCompareForm model = new MoviesCompareForm();
        model.setMovie1(getMovieFromJson(movieUnchangedJson1));
        model.setMovie2(getMovieFromJson(movieToUpdateJson2));
        
        // Check for no updates first
        model = mvcFacade.checkForLocalModifications(model);
        String updateMessage = model.getMessage();
        assertFalse(updateMessage.contains(model.getMovie1().getTitle()) || updateMessage.contains(model.getMovie2().getTitle()));
        
        // Update second movie only
        model.getMovie2().setFavourite(true);
        model.getMovie2().setRating(10.0f);
        model.getMovie2().setChanged(true);
        model = mvcFacade.checkForLocalModifications(model);
        updateMessage = model.getMessage();
        
        assertFalse(updateMessage.contains(model.getMovie1().getTitle()));
        assertTrue(updateMessage.contains(model.getMovie2().getTitle()));
    }
    
    /**
     * Test the {@link MoviesMvcFacade#saveMovies(MoviesCompareForm)} method.
     * 
     * @throws Exception in case of any failure
     */
    @Test
    public void saveMoviesTest003() throws Exception
    {
        String movie1ToSave003 = getTestProperty("web.test.movie1ToSave003");
        String movie2ToSave003 = getTestProperty("web.test.movie2ToSave003");
        MoviesCompareForm model = new MoviesCompareForm();
        model.setMovieTitle1(movie1ToSave003);
        model.setMovieTitle2(movie2ToSave003);
        
        // Prepare the model to retrieve movies from OMDb
        model = mvcFacade.prepareComparisonResults(model);
        
        // Change rating to check DB insert
        model.getMovie1().setRating(10.0F);
        model.getMovie1().setChanged(true);
        model.getMovie2().setRating(0F);
        model.getMovie2().setChanged(true);
        
        // Save the movies
        mvcFacade.saveMovies(model);
        
        // Get the two movies from DB
        model = mvcFacade.prepareComparisonResults(model);
        
        // Compare ratings and changed flag
        assertEquals(getMatchingValuesErrorMsg(MOVIE_RATING_LITERAL_KEY), new Float(10.0F), model.getMovie1().getRating());
        assertTrue(model.getMovie1().getChanged());
        
        assertEquals(getMatchingValuesErrorMsg(MOVIE_RATING_LITERAL_KEY), new Float(0F), model.getMovie2().getRating());
        assertTrue(model.getMovie2().getChanged());
    }
    
    /**
     * Test the {@link MoviesMvcFacade#getCachedMovies()} method.
     * 
     * @throws Exception in case of any failure
     */
    @Test
    public void getCachedTest004() throws Exception
    {
        String insertedMovie1 = getTestProperty("web.test.betterMovie001");
        String insertedMovie2 = getTestProperty("web.test.worseMovie001");
        String insertedMovie3 = getTestProperty("web.test.movie1ToSave003");
        String insertedMovie4 = getTestProperty("web.test.movie2ToSave003");
        
        // Insert the four movies through the MVC facade
        MoviesCompareForm model = new MoviesCompareForm();
        model.setMovieTitle1(insertedMovie1);
        model.setMovieTitle2(insertedMovie2);
        mvcFacade.prepareComparisonResults(model);
        mvcFacade.saveMovies(model);
        
        // Two movies here
        List<MovieEntity> cachedMovies = mvcFacade.getCachedMovies();
        assertEquals(getMatchingValuesErrorMsg(NUM_OF_MOVIES_LITERAL_KEY), 2, cachedMovies.size());
        
        model = new MoviesCompareForm();
        model.setMovieTitle1(insertedMovie3);
        model.setMovieTitle2(insertedMovie4);
        mvcFacade.prepareComparisonResults(model);
        mvcFacade.saveMovies(model);
        
        // Four here
        cachedMovies = mvcFacade.getCachedMovies();
        assertEquals(getMatchingValuesErrorMsg(NUM_OF_MOVIES_LITERAL_KEY), 4, cachedMovies.size());
        
        // Insert twice all movies (already cached movies are not added)
        mvcFacade.prepareComparisonResults(model);
        mvcFacade.saveMovies(model);
        
        model = new MoviesCompareForm();
        model.setMovieTitle1(insertedMovie1);
        model.setMovieTitle2(insertedMovie2);
        mvcFacade.prepareComparisonResults(model);
        mvcFacade.saveMovies(model);
        
        // Always four
        cachedMovies = mvcFacade.getCachedMovies();
        assertEquals(getMatchingValuesErrorMsg(NUM_OF_MOVIES_LITERAL_KEY), 4, cachedMovies.size());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void initConfigFilename()
    {
        setConfigFilename(WEB_TEST_CONFIG_FILENAME);
    }    
}
