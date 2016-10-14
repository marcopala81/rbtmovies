package ie.brandtone.moviescomparator.wsclient.exception;

import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;
import static ie.brandtone.moviescomparator.utils.Constants.TO_STRING_NESTED_CAUSE_KEY;
import static ie.brandtone.moviescomparator.utils.Constants.MOVIE_NOT_FOUND_ERROR_MSG_KEY;

/**
 * Used for reporting an exception while retrieving a movie.
 * 
 * @author Marco Pala
 */
public class MovieNotFoundException extends Exception
{
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -7409835810626937480L;
	
	/**
	 * Public constructor with the movie title.
	 * 
	 * @param title The requested movie title
	 */
	public MovieNotFoundException(String title)
	{
		super(getMessageFromBundle(MOVIE_NOT_FOUND_ERROR_MSG_KEY, title));
	}
	
	/**
	 * Public constructor with the original cause to encapsulate and the movie title.
	 * 
	 * @param cause The original cause to encapsulate
	 * @param title The requested movie title
	 */
	public MovieNotFoundException(Throwable cause, String title)
	{
		this(title);
		this.initCause(cause);
	}
	
	/**
	 * Overrides the superclass method to append the {@link MovieNotFoundException} nested cause.
	 * 
	 * @return The {@link MovieNotFoundException} string representation plus the nested cause string representation (if any)
	 */
	@Override
	public String toString()
	{
		StringBuilder traceMsg = new StringBuilder();
		Throwable nestedCause = this.getCause();
		
		if (nestedCause != null)
		{
			traceMsg.append(getMessageFromBundle(TO_STRING_NESTED_CAUSE_KEY, super.toString(), nestedCause.toString()));
		}
		else
		{
			traceMsg.append(super.toString());
		}
		
		return traceMsg.toString();
	}
}
