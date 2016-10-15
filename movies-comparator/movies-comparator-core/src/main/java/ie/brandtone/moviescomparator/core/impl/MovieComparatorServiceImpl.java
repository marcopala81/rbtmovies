package ie.brandtone.moviescomparator.core.impl;

import ie.brandtone.moviescomparator.dao.Movie;
import ie.brandtone.moviescomparator.dao.exception.BadMovieFormatException;
import ie.brandtone.moviescomparator.wsclient.MovieRetrieverService;
import ie.brandtone.moviescomparator.wsclient.exception.MovieNotFoundException;
import ie.brandtone.moviescomparator.wsclient.exception.WsClientConfigException;
import ie.brandtone.moviescomparator.wsclient.exception.WsClientException;
import ie.brandtone.moviescomparator.wsclient.impl.OMDbApiRestClient;
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
     * The Singleton instance of this movie comparator implementation.
     */
    private static MovieComparatorService instance;

    /**
     * The movie retriever service.
     */
    private static MovieRetrieverService mrs;

    /**
     * {@inheritDoc}
     */
    @Override
    public Movie compareMoviesByRating(String title1, String title2) throws MovieComparatorException
    {
        Movie movie1 = null;
        Movie movie2 = null;
        Movie bestOne = null;

        // Retrieve both movies to compare
        try
        {
            movie1 = mrs.getMovieByTitle(title1);
            movie2 = mrs.getMovieByTitle(title2);
        }
        catch (MovieNotFoundException | BadMovieFormatException | WsClientConfigException e)
        {
            throw new MovieComparatorException(e);
        }

        // Begin comparison by rating
        if (movie1.getRating() > movie2.getRating())
        {
            bestOne = movie1;
        }
        else if (movie1.getRating() < movie2.getRating())
        {
            bestOne = movie2;
        }

        return bestOne;
    }

    /**
     * Get the {@link MovieComparatorServiceImpl} Singleton instance.
     * 
     * @return The {@link MovieComparatorServiceImpl} Singleton instance
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
}
