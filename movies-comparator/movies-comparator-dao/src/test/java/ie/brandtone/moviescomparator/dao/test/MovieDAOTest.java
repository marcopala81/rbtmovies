package ie.brandtone.moviescomparator.dao.test;

import static ie.brandtone.moviescomparator.dao.AbstractMovieFactory.getMovieFromTestConfig;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_TITLE_LITERAL_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getMatchingValuesErrorMsg;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import ie.brandtone.moviescomparator.dao.MovieDAO;
import ie.brandtone.moviescomparator.dao.entity.MovieEntity;
import ie.brandtone.moviescomparator.utils.exceptions.TestConfigException;

/**
 * Class test for testing the in-memory Movie Database with the {@link MovieDAO} interface.
 * 
 * @author Marco Pala
 */
@ContextConfiguration(locations = "classpath:dao-test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class MovieDAOTest extends BaseDAOModuleTest
{
    /**
     * The Movie DAO instance.
     */
    @Autowired
    private MovieDAO movieDAO;
    
    /**
     * Test the {@link MovieDAO#insertMovie(MovieEntity)} method.
     * 
     * @throws Exception in case of any failure
     */
    @Test
    @Transactional
    @Rollback(true)
    public void insertMovieTest001() throws Exception
    {        
        // Create MovieEntity and insert in the DB
        MovieEntity movieEntity1 = getDaoTestMovie1();      
        Integer movieId = movieDAO.insertMovie(movieEntity1);

        // Check title from DB
        MovieEntity movieFromDb = movieDAO.getMovieById(movieId);
        assertEquals(movieEntity1.getTitle(), movieFromDb.getTitle());
        movieDAO.deleteMovie(movieId);
    }
    
    /**
     * Test the {@link MovieDAO#updateMovie(MovieEntity)} method.
     * 
     * @throws Exception in case of any failure
     */
    @Test
    @Transactional
    @Rollback(true)
    public void updateMovieTest002() throws Exception
    {
        // New Movie entity
        String idKey = "dao.test.update.movie2.id";
        String titleKey = "dao.test.update.movie2.title";
        String ratingKey = "dao.test.update.movie2.rating";
        
        MovieEntity movieEntity2 = getMovieFromTestConfig(this, idKey, titleKey, ratingKey);
        Integer movieId = movieDAO.insertMovie(movieEntity2);
        MovieEntity movieFromDb = movieDAO.getMovieById(movieId);
        // Before the update, check rating and favourite flag
        assertEquals(new Float(getTestProperty(ratingKey)), movieFromDb.getRating());
        assertFalse(movieFromDb.getFavourite());
        
        // New rating to set
        Float newRating = 10.0f;
        movieEntity2.setRating(newRating);
        movieEntity2.setFavourite(true);
        
        // Update Movie in the DB
        movieDAO.updateMovie(movieEntity2);
        
        // Check new rating and favourite flag
        MovieEntity movieUpdatedFromDb = movieDAO.getMovieById(movieId);
        assertEquals(newRating, movieUpdatedFromDb.getRating());
        assertTrue(movieUpdatedFromDb.getFavourite());
        movieDAO.deleteMovie(movieId);
    }
    
    /**
     * Test the {@link MovieDAO#deleteMovie(Integer)} method.
     * 
     * @throws Exception in case of any failure
     */
    @Test
    @Transactional
    @Rollback(true)
    public void deleteMovieTest003() throws Exception
    {
        // New Movie entity
        String idKey = "dao.test.delete.movie3.id";
        String titleKey = "dao.test.delete.movie3.title";
        String ratingKey = "dao.test.delete.movie3.rating";
        
        MovieEntity movieEntity3 = getMovieFromTestConfig(this, idKey, titleKey, ratingKey);
        Integer movieId = movieDAO.insertMovie(movieEntity3);
        MovieEntity movieFromDb = movieDAO.getMovieById(movieId);
        // Before the delete, check if object already exist
        assertNotNull(movieFromDb);
                
        // Delete Movie in the DB
        movieDAO.deleteMovie(movieId);
        
        // Check deleted
        MovieEntity movieDeletedFromDb = movieDAO.getMovieById(movieId);

        // Check new rating
        assertNull(movieDeletedFromDb);
    }
    
    /**
     * Test the {@link MovieDAO#getMovieById(Integer)} method.
     * 
     * @throws Exception in case of any failure
     */
    @Test
    @Transactional
    @Rollback(true)
    public void getMovieByIdTest004() throws Exception
    {        
        MovieEntity movieEntity = getDaoTestMovie1();
        Integer movieId = movieDAO.insertMovie(movieEntity);
        MovieEntity movieFromDb = movieDAO.getMovieById(movieId);
        // Check title
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), movieEntity.getTitle(), movieFromDb.getTitle());
        movieDAO.deleteMovie(movieId);
    }
    
    /**
     * Test the {@link MovieDAO#getMovieByTitle(String)} method.
     * 
     * @throws Exception in case of any failure
     */
    @Test
    @Transactional
    @Rollback(true)
    public void getMovieByTitleTest005() throws Exception
    {
        MovieEntity movieEntity = getDaoTestMovie1();
        Integer movieId = movieDAO.insertMovie(movieEntity);
        MovieEntity movieFromDb = movieDAO.getMovieByTitle(movieEntity.getTitle());
        // Check title
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), movieEntity.getTitle(), movieFromDb.getTitle());
        movieDAO.deleteMovie(movieId);
    }
    
    /**
     * Test the {@link MovieDAO#getMovieById(Integer)} method.
     * 
     * @throws Exception in case of any failure
     */
    @Test
    @Transactional
    @Rollback(true)
    public void getAllMoviesTest006() throws Exception
    {
        // New Movie entities
        MovieEntity movieEntity1 = getDaoTestMovie1();
        
        String idKey = "dao.test.update.movie2.id";
        String titleKey = "dao.test.update.movie2.title";
        String rating = "dao.test.update.movie2.rating";
        MovieEntity movieEntity2 = getMovieFromTestConfig(this, idKey, titleKey, rating);
        
        idKey = "dao.test.delete.movie3.id";
        titleKey = "dao.test.delete.movie3.title";
        rating = "dao.test.delete.movie3.rating";
        MovieEntity movieEntity3 = getMovieFromTestConfig(this, idKey, titleKey, rating);
        
        movieDAO.insertMovie(movieEntity1);
        movieDAO.insertMovie(movieEntity2);
        movieDAO.insertMovie(movieEntity3);
        
        // Get all movies
        List<MovieEntity> moviesList = movieDAO.getAllMovies();
        // Check result count
        assertEquals(3, moviesList.size());
    }
    
    /**
     *  Common subrutine for getting the <code>dao.test.insert.movie1</code> from the configuration file.
     *  
     * @return The movie <code>dao.test.insert.movie1</code>
     * 
     * @throws TestConfigException in case of any configuration issue
     */
    private MovieEntity getDaoTestMovie1() throws TestConfigException
    {
        String idKey = "dao.test.insert.movie1.id";
        String titleKey = "dao.test.insert.movie1.title";
        String ratingKey = "dao.test.insert.movie1.rating";
        return getMovieFromTestConfig(this, idKey, titleKey, ratingKey);
    }    
}



