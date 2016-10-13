package ie.brandtone.moviescomparator.wsclient;

import static ie.brandtone.moviescomparator.wsclient.Constants.SPACE;
import static ie.brandtone.moviescomparator.wsclient.Constants.NESTED_CAUSE_MSG;
import static ie.brandtone.moviescomparator.wsclient.Constants.MOVIE_NOT_FOUND_ERROR_MSG;

/**
 * Used for reporting an exception while retrieving a movie.
 * <br>
 * TODO Move to a proper exception package.
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
		super(String.format(MOVIE_NOT_FOUND_ERROR_MSG, title));
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
		StringBuilder traceMsg = new StringBuilder(super.toString());
		Throwable nestedCause = this.getCause();
		
		if (nestedCause != null)
		{
			traceMsg.append(SPACE).append(String.format(NESTED_CAUSE_MSG, nestedCause.toString()));
		}
		
		return traceMsg.toString();
	}
}
