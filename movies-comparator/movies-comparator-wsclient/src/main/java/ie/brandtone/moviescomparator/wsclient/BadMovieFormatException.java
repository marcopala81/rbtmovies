package ie.brandtone.moviescomparator.wsclient;

import static ie.brandtone.moviescomparator.wsclient.Constants.MOVIE_FORMAT_ERROR_MSG;

/**
 * Used for reporting an exception while creating a movie.
 * <br>
 * TODO Move to a proper exception package.
 * 
 * @author Marco Pala
 */
public class BadMovieFormatException extends Exception
{
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 6471733158262831074L;

	/**
	 * Public constructor with the original cause to encapsulate.
	 * 
	 * @param cause The original cause to encapsulate
	 */
	public BadMovieFormatException(Throwable cause)
	{
		super(MOVIE_FORMAT_ERROR_MSG, cause);
	}	
}
