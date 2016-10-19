package ie.brandtone.moviescomparator.core.impl;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.CHECKING_FAVOURITE_MOVIE_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.COMPARING_MOVIES_BY_RATING_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.FAVOURITE_MOVIE_FOUND_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.HIGHEST_RATED_MOVIE_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.NO_FAVOURITE_MOVIE_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.SAME_RATING_MOVIES_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getEnteringMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getExitingMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import ie.brandtone.moviescomparator.core.MovieComparatorService;
import ie.brandtone.moviescomparator.core.exception.MovieComparatorException;
import ie.brandtone.moviescomparator.dao.entity.MovieEntity;

/**
 * Implements the Movies Comparator interface (adhere to the <i>Singleton</i> pattern).
 * 
 * @author Marco Pala
 */
@Service
public class MovieComparatorServiceImpl implements MovieComparatorService
{
    /**
     * The Apache Log4j logger.
     */
    private static final Logger LOGGER = Logger.getLogger(MovieComparatorServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieEntity compareMoviesByRating(MovieEntity movie1, MovieEntity movie2) throws MovieComparatorException
    {
        String methodName = "compareMoviesByRating(MovieEntity, MovieEntity)";
        LOGGER.info(getEnteringMessage(methodName));        
        LOGGER.info(getMessageFromBundle(COMPARING_MOVIES_BY_RATING_MSG_KEY, movie1.getTitle(), movie2.getTitle()));
        
        // Check if there's a favourite movie to skip comparison
        LOGGER.info(getMessageFromBundle(CHECKING_FAVOURITE_MOVIE_MSG_KEY));
        MovieEntity bestOne = checkFavourite(movie1, movie2);
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
     * Check if any of the two given movies is marked as favourite and return the marked one (or <code>null</code> if there's no favourite).
     * 
     * @param movie1 The first movie to check
     * @param movie2 The second movie to check
     * 
     * @return The favourite one (or <code>null</code> if there's no favourite)
     */
    private MovieEntity checkFavourite(MovieEntity movie1, MovieEntity movie2)
    {
        MovieEntity favouriteOne = null;
        
        if (movie1.getFavourite())
        {
            favouriteOne = movie1;
        }
        else if (movie2.getFavourite())
        {
            favouriteOne = movie2;
        }

        return favouriteOne;
    }    
}
