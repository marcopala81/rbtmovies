package ie.brandtone.moviescomparator.wsclient;

import ie.brandtone.moviescomparator.dao.Movie;
import ie.brandtone.moviescomparator.dao.exception.BadMovieFormatException;
import ie.brandtone.moviescomparator.wsclient.exception.MovieNotFoundException;

/**
 * Defines the methods to retrieve movies from a given source.
 * 
 * @author Marco Pala
 * @version 1.0.0
 */
public interface MovieRetrieverService
{
	/**
	 * For any given title, retrieves the matching movie object.
	 * 
	 * @param title The requested title
	 * 
	 * @return The object representing the retrieved movie
	 * 
	 * @throws MovieNotFoundException in case of any issue during the movie retrieval.
	 * @throws BadMovieFormatException in case of a bad JSON input format.
	 * 
	 * @since v1.0.0
	 */
	public Movie getMovieByTitle(String title) throws MovieNotFoundException, BadMovieFormatException;
}
