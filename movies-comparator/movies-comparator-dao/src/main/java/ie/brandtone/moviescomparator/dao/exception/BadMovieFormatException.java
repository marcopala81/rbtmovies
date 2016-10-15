package ie.brandtone.moviescomparator.dao.exception;

import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_FORMAT_ERROR_MSG_KEY;

/**
 * Used for reporting an exception while creating a movie.
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
        super(getMessageFromBundle(MOVIE_FORMAT_ERROR_MSG_KEY), cause);
    }
}
