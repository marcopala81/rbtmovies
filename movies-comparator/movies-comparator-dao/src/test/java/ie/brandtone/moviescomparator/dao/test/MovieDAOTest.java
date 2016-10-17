package ie.brandtone.moviescomparator.dao.test;

import static ie.brandtone.moviescomparator.dao.AbstractMovieFactory.newMovieEntity;
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

/**
 * Class test for testing the in-memory Movie Database with the {@link MovieDAO} interface.
 * 
 * @author Marco Pala
 */
@ContextConfiguration(locations = "classpath:test-dao-context.xml")
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
        // New Movie entity
        String id = getTestProperty("dao.test.insert.movie1.id");
        String title = getTestProperty("dao.test.insert.movie1.title");
        String rating = getTestProperty("dao.test.insert.movie1.rating");
        
        // Create MovieEntity and insert in the DB
        MovieEntity movieEntity1 = newMovieEntity(id, title, Float.parseFloat(rating));        
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
        String id = getTestProperty("dao.test.update.movie2.id");
        String title = getTestProperty("dao.test.update.movie2.title");
        Float rating = Float.parseFloat(getTestProperty("dao.test.update.movie2.rating"));
        
        MovieEntity movieEntity2 = newMovieEntity(id, title, rating);
        Integer movieId = movieDAO.insertMovie(movieEntity2);
        MovieEntity movieFromDb = movieDAO.getMovieById(movieId);
        // Before the update, check rating and favourite flag
        assertEquals(rating, movieFromDb.getRating());
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
        String id = getTestProperty("dao.test.delete.movie3.id");
        String title = getTestProperty("dao.test.delete.movie3.title");
        Float rating = Float.parseFloat(getTestProperty("dao.test.delete.movie3.rating"));
        
        MovieEntity movieEntity3 = newMovieEntity(id, title, rating);
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
        // New Movie entity
        String id = getTestProperty("dao.test.insert.movie1.id");
        String title = getTestProperty("dao.test.insert.movie1.title");
        Float rating = Float.parseFloat(getTestProperty("dao.test.insert.movie1.rating"));
        
        MovieEntity movieEntity = newMovieEntity(id, title, rating);
        Integer movieId = movieDAO.insertMovie(movieEntity);
        MovieEntity movieFromDb = movieDAO.getMovieById(movieId);
        // Check title
        assertEquals(getMatchingValuesErrorMsg(MOVIE_TITLE_LITERAL_KEY), title, movieFromDb.getTitle());
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
    public void getAllMoviesTest005() throws Exception
    {
        // New Movie entities
        String id = getTestProperty("dao.test.insert.movie1.id");
        String title = getTestProperty("dao.test.insert.movie1.title");
        Float rating = Float.parseFloat(getTestProperty("dao.test.insert.movie1.rating"));
        MovieEntity movieEntity1 = newMovieEntity(id, title, rating);
        
        id = getTestProperty("dao.test.update.movie2.id");
        title = getTestProperty("dao.test.update.movie2.title");
        rating = Float.parseFloat(getTestProperty("dao.test.update.movie2.rating"));
        MovieEntity movieEntity2 = newMovieEntity(id, title, rating);
        
        id = getTestProperty("dao.test.delete.movie3.id");
        title = getTestProperty("dao.test.delete.movie3.title");
        rating = Float.parseFloat(getTestProperty("dao.test.delete.movie3.rating"));
        MovieEntity movieEntity3 = newMovieEntity(id, title, rating);
        
        movieDAO.insertMovie(movieEntity1);
        movieDAO.insertMovie(movieEntity2);
        movieDAO.insertMovie(movieEntity3);
        
        // Get all movies
        List<MovieEntity> moviesList = movieDAO.getAllMovies();
        // Check result count
        assertEquals(3, moviesList.size());
    }    
}



