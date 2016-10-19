package ie.brandtone.moviescomparator.web.exception;

/**
 * Used for reporting an exception while initializing the movie comparator service.
 * 
 * @author Marco Pala
 */
public class MvcFacadeException extends Exception
{
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -1767022247936446975L;

    /**
     * Public constructor with the original cause to encapsulate.
     * 
     * @param cause The original cause to encapsulate
     */
    public MvcFacadeException(Throwable cause)
    {
        super(cause);
    }
}