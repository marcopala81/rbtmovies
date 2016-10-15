package ie.brandtone.moviescomparator.core.exception;

/**
 * Used for reporting an exception while initializing the movie comparator service.
 * 
 * @author Marco Pala
 */
public class MovieComparatorException extends Exception
{
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -2206020276281776217L;

    /**
     * Public constructor with the original cause to encapsulate.
     * 
     * @param cause The original cause to encapsulate
     */
    public MovieComparatorException(Throwable cause)
    {
        super(cause);
    }
}
