package ie.brandtone.moviescomparator.wsclient;

import ie.brandtone.moviescomparator.dao.entity.MovieEntity;
import ie.brandtone.moviescomparator.dao.exception.BadMovieFormatException;
import ie.brandtone.moviescomparator.wsclient.exception.MovieNotFoundException;
import ie.brandtone.moviescomparator.wsclient.exception.WsClientConfigException;

/**
 * Defines the methods to retrieve movies from a given source.
 * 
 * @author Marco Pala
 * 
 * @version 1.0.0
 */
public interface MovieRetrieverService
{
    /**
     * For any given title, retrieves the matching movie and returns a {@link MovieEntity}.
     * 
     * @param title The requested title
     * 
     * @return The MovieEntity representing the retrieved movie
     * 
     * @throws MovieNotFoundException in case of any issue during the movie retrieval
     * @throws BadMovieFormatException in case of a bad JSON input format
     * @throws WsClientConfigException in case of any webservice client configuration issue
     * 
     * @since v1.0.0
     */
    MovieEntity getMovieByTitle(String title) throws MovieNotFoundException, BadMovieFormatException, WsClientConfigException;
}
