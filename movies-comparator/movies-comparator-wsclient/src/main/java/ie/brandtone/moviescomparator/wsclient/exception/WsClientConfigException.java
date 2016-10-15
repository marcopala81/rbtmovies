package ie.brandtone.moviescomparator.wsclient.exception;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.WSCLIENT_CONFIG_ERROR_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;

/**
 * Used for reporting an exception related to the webservice client configuration.
 * 
 * @author Marco Pala
 */
public class WsClientConfigException extends Exception
{
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 303085435015891398L;

    /**
     * Create an OMDb API configuration exception with a native cause.
     * 
     * @param cause The native cause to encapsulate
     */
    public WsClientConfigException(Throwable cause)
    {
        super(getMessageFromBundle(WSCLIENT_CONFIG_ERROR_MSG_KEY), cause);
    }
}