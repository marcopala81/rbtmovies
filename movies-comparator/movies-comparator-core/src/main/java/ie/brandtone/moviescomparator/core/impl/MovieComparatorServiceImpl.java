package ie.brandtone.moviescomparator.core.impl;

import ie.brandtone.moviescomparator.dao.Movie;
import ie.brandtone.moviescomparator.dao.exception.BadMovieFormatException;
import ie.brandtone.moviescomparator.wsclient.MovieRetrieverService;
import ie.brandtone.moviescomparator.wsclient.exception.MovieNotFoundException;
import ie.brandtone.moviescomparator.wsclient.exception.WsClientConfigException;
import ie.brandtone.moviescomparator.wsclient.exception.WsClientException;
import ie.brandtone.moviescomparator.wsclient.impl.OMDbApiRestClient;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.COMPARING_MOVIES_BY_RATING_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.CHECKING_FAVOURITE_MOVIE_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.NO_FAVOURITE_MOVIE_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.FAVOURITE_MOVIE_FOUND_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.HIGHEST_RATED_MOVIE_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.SAME_RATING_MOVIES_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.COMPARATOR_SERVICE_ERROR_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getEnteringMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getExitingMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getLeavingMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;

import org.apache.log4j.Logger;

import ie.brandtone.moviescomparator.core.MovieComparatorService;
import ie.brandtone.moviescomparator.core.exception.MovieComparatorException;

/**
 * Implements the Movie Comparator interface (adhere to the <i>Singleton</i> pattern).
 * 
 * @author Marco Pala
 */
public class MovieComparatorServiceImpl implements MovieComparatorService
{
    /**
     * The <i>Singleton</i> instance of this movie comparator implementation.
     */
    private static MovieComparatorService instance;

    /**
     * The movie retriever service.
     */
    private static MovieRetrieverService mrs;

    /**
     * The Apache Log4j logger.
     */
    private static final Logger LOGGER = Logger.getLogger(MovieComparatorServiceImpl.class);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Movie compareMoviesByRating(String title1, String title2) throws MovieComparatorException
    {
        String methodName = "compareMoviesByRating(String, String)";
        LOGGER.info(getEnteringMessage(methodName));
        
        Movie movie1 = null;
        Movie movie2 = null;

        // Retrieve both movies to compare
        try
        {
            movie1 = mrs.getMovieByTitle(title1);
            movie2 = mrs.getMovieByTitle(title2);
        }
        catch (MovieNotFoundException | BadMovieFormatException | WsClientConfigException e)
        {
            LOGGER.error(getMessageFromBundle(COMPARATOR_SERVICE_ERROR_MSG_KEY));
            LOGGER.info(getLeavingMessage(methodName));
            throw new MovieComparatorException(e);
        }

        LOGGER.info(getExitingMessage(methodName));
        return compareMoviesByRating(movie1, movie2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Movie compareMoviesByRating(Movie movie1, Movie movie2) throws MovieComparatorException
    {
        String methodName = "compareMoviesByRating(Movie, Movie)";
        LOGGER.info(getEnteringMessage(methodName));        
        LOGGER.info(getMessageFromBundle(COMPARING_MOVIES_BY_RATING_MSG_KEY, movie1.getTitle(), movie2.getTitle()));
        
        // Check if there's a favourite movie to skip comparison
        LOGGER.info(getMessageFromBundle(CHECKING_FAVOURITE_MOVIE_MSG_KEY));
        Movie bestOne = checkFavourite(movie1, movie2);
        boolean bestOneFound = false;
        
        if (bestOne == null)
        {
            LOGGER.info(getMessageFromBundle(NO_FAVOURITE_MOVIE_MSG_KEY, movie1.getTitle(), movie2.getTitle()));
            // Begin comparison by rating
            if (movie1.getRating() > movie2.getRating())
            {
                bestOne = movie1;
                bestOneFound = true;
            }
            else if (movie1.getRating() < movie2.getRating())
            {
                bestOne = movie2;
                bestOneFound = true;
            }
            else
            {
                LOGGER.info(getMessageFromBundle(SAME_RATING_MOVIES_MSG_KEY));
            }
        }
        else
        {
            LOGGER.info(getMessageFromBundle(FAVOURITE_MOVIE_FOUND_MSG_KEY, bestOne.getTitle()));
        }
        
        if (bestOneFound)
        {
            LOGGER.info(getMessageFromBundle(HIGHEST_RATED_MOVIE_MSG_KEY, bestOne.getTitle()));
        }        
        
        LOGGER.info(getExitingMessage(methodName));
        
        return bestOne;
    }
    
    /**
     * Get the {@link MovieComparatorServiceImpl} <i>Singleton</i> instance.
     * 
     * @return The MovieComparatorServiceImpl <i>Singleton</i> instance
     * 
     * @throws MovieComparatorException in case of any issue during initialization
     */
    public static MovieComparatorService getInstance() throws MovieComparatorException
    {
        // Initialize only once
        if (instance == null)
        {
            instance = (MovieComparatorService) new MovieComparatorServiceImpl();
        }

        if (mrs == null)
        {
            try
            {
                mrs = OMDbApiRestClient.getInstance();
            }
            catch (WsClientException wse)
            {
                throw new MovieComparatorException(wse);
            }
        }

        return instance;
    }
    
    /**
     * Check if any of the two given movies is marked as favourite and return the marked one (or <code>null</code> if there's no favourite).
     * 
     * @param movie1 The first movie to check
     * @param movie2 The second movie to check
     * 
     * @return The favourite one (or <code>null</code> if there's no favourite)
     */
    private Movie checkFavourite(Movie movie1, Movie movie2)
    {
        Movie favouriteOne = null;
        
        if (movie1.isFavourite())
        {
            favouriteOne = movie1;
        }
        else if (movie2.isFavourite())
        {
            favouriteOne = movie2;
        }

        return favouriteOne;
    }    
}
