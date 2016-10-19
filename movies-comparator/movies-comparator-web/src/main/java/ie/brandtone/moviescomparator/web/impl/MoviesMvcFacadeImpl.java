package ie.brandtone.moviescomparator.web.impl;

import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;

import java.util.List;

import static ie.brandtone.moviescomparator.utils.Commons.BR;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_UPDATED_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.NOTHING_CHANGED_MSG_KEY;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.brandtone.moviescomparator.core.MovieComparatorService;
import ie.brandtone.moviescomparator.core.exception.MovieComparatorException;
import ie.brandtone.moviescomparator.dao.MovieDAO;
import ie.brandtone.moviescomparator.dao.entity.MovieEntity;
import ie.brandtone.moviescomparator.dao.exception.BadMovieFormatException;
import ie.brandtone.moviescomparator.web.MoviesMvcFacade;
import ie.brandtone.moviescomparator.web.exception.MvcFacadeException;
import ie.brandtone.moviescomparator.web.model.MoviesCompareForm;
import ie.brandtone.moviescomparator.wsclient.MovieRetrieverService;
import ie.brandtone.moviescomparator.wsclient.exception.MovieNotFoundException;
import ie.brandtone.moviescomparator.wsclient.exception.WsClientConfigException;

/**
 * Implements the Movies Comparator MVC facade interface (adhere to the <i>Singleton</i> pattern).
 * 
 * @author Marco Pala
 */
@Service
public class MoviesMvcFacadeImpl implements MoviesMvcFacade
{
    /**
     * The Movies Comparator service.
     */
    @Autowired
    private MovieComparatorService movieComparator;
    
    /**
     * The Movies Retriever service.
     */
    @Autowired
    private MovieRetrieverService movieRetriever;
    
    /**
     * The Movie DAO service.  
     */
    @Autowired
    private MovieDAO movieDAO;
        
    /**
     * {@inheritDoc}
     */
    @Override
    public MoviesCompareForm prepareComparisonResults(MoviesCompareForm model) throws MvcFacadeException
    {        
        // Search for the movies entities (either in the local DB or from the remote webservice)
        MovieEntity movie1 = fetchMovieFromDbOrWs(model.getMovieTitle1());
        MovieEntity movie2 = fetchMovieFromDbOrWs(model.getMovieTitle2());
        
        MovieEntity winner = null;
        try
        {
            // Compare the movies to find the winner
            winner = movieComparator.compareMoviesByRating(movie1, movie2);
        }
        catch (MovieComparatorException e)
        {
            throw new MvcFacadeException(e);
        }
        
        // Fill the Model
        model.setWinnerMovie(winner);
        model.setMovie1(movie1);
        model.setMovie2(movie2);
        
        return model;
    }
        
    /**
     * {@inheritDoc}
     */
    @Override
    public MoviesCompareForm checkForLocalModifications(MoviesCompareForm model)
    {
        MovieEntity movie1 = model.getMovie1();
        MovieEntity movie2 = model.getMovie2();
        StringBuilder msg = new StringBuilder();        
        
        if (movie1.getChanged() || movie2.getChanged())
        {
            if (movie1.getChanged())
            {
                msg.append(getMessageFromBundle(MOVIE_UPDATED_MSG_KEY, movie1.getTitle()));
            }            
            if (movie2.getChanged())
            {
                if (movie1.getChanged())
                {
                    // LINE BREAK
                    msg.append(BR).append(BR);
                }
                msg.append(getMessageFromBundle(MOVIE_UPDATED_MSG_KEY, movie2.getTitle()));
            }
        }
        else
        {
            // NO UPDATES
            msg.append(getMessageFromBundle(NOTHING_CHANGED_MSG_KEY));
        }
        
        // Set the update msg
        model.setMessage(msg.toString());
        
        return model;
    }
    
    /**
     * {@inheritDoc} 
     */    
    @Override    
    public void saveMovies(MoviesCompareForm model) throws MvcFacadeException
    {
        // Save movies in the local database (insert or update)
        insertOrUpdate(model.getMovie1());
        insertOrUpdate(model.getMovie2());
    }
    
    /**
     * {@inheritDoc} 
     */    
    @Override
    public List<MovieEntity> getCachedMovies() throws MvcFacadeException
    {
        List<MovieEntity> cachedMovies = null;
        
        try
        {
            cachedMovies = movieDAO.getAllMovies();
        }
        catch (Exception e)
        {
            // TODO LOG DB ERROR
            throw new MvcFacadeException(e);
        }
        
        return cachedMovies;
    }
        
    /**
     * Search the given movie title in the local DB:
     * <br>
     * <ul>
     *  <li>if movie is in the local DB then is returned</li>
     *  <li>otherwise, movie is retrieved from the OMDb API webservice, then cached for local modifications</li>
     * </ul>
     * 
     * @param movieTitle The movie title to fetch
     * 
     * @return The {@link MovieEntity} object representing the movie
     * 
     * @throws MvcFacadeException in case of any error during fetch
     */
    private MovieEntity fetchMovieFromDbOrWs(String movieTitle) throws MvcFacadeException 
    {
        MovieEntity movie = null;
        String titleCapital = movieTitle.toUpperCase();
        
        // Search in the local DB first
        try
        {
            movie = movieDAO.getMovieByTitle(titleCapital);    
        }
        catch (Exception e)
        {
            // TODO LOG DB ERROR
            throw new MvcFacadeException(e);
        }
        
        if (movie == null)
        {
            // Movie not found in the DB
            try
            {
                // Retrieve movie from the OMDb API webservice and persist in memory
                movie = movieRetriever.getMovieByTitle(movieTitle);
                
                // Force title to UPPERCASE to uniform search
                movie.setTitle(titleCapital);
                movieDAO.insertMovie(movie);
            }
            catch (WsClientConfigException | MovieNotFoundException | BadMovieFormatException e)
            {
                throw new MvcFacadeException(e);
            }            
        }
                   
        return movie;
    }
    
    /**
     * Subroutine to choose the correct transaction (input or update).
     * 
     * @param movie The movie to persist
     * 
     * @throws MvcFacadeException in case of any database issue
     */
    private void insertOrUpdate(MovieEntity movie) throws MvcFacadeException
    {
        try
        {
            MovieEntity movieToUpdate = movieDAO.getMovieByTitle(movie.getTitle());
                    
            if (movieToUpdate != null && movie.getChanged())
            {
                movie.setId(movieToUpdate.getId());
                movieDAO.updateMovie(movie);
            }
            else
            {
                movieDAO.insertMovie(movie);
            }
        }
        catch (Exception e)
        {
            // TODO LOG DB ERROR
            throw new MvcFacadeException(e);
        }
    }
}
