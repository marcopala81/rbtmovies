package ie.brandtone.moviescomparator.wsclient.exception;

import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;
import static ie.brandtone.moviescomparator.utils.Constants.WS_CLIENT_ERROR_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Constants.WS_CLIENT_HTTP_ERROR_MSG_KEY;

/**
 * Used for reporting an exception while initializing a webservice client.
 * 
 * @author Marco Pala
 */
public class WsClientException extends Exception
{
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 9165261220809277327L;
		
	/**
	 * Create a webservice client exception with a native cause.
	 * 
	 * @param cause The native cause to encapsulate
	 */
	public WsClientException(Throwable cause)
	{
		super(getMessageFromBundle(WS_CLIENT_ERROR_MSG_KEY), cause);
	}
	
	/**
	 * Create a webservice client exception with an HTTP error code.
	 * 
	 * @param httpErrorCode The HTTP error code to encapsulate
	 */
	public WsClientException(int httpErrorCode)
	{
		super(getMessageFromBundle(WS_CLIENT_HTTP_ERROR_MSG_KEY, String.valueOf(httpErrorCode)));
	}	
}
