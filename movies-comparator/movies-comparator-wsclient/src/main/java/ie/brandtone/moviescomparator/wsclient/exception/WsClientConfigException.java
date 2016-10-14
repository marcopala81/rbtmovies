package ie.brandtone.moviescomparator.wsclient.exception;

import static ie.brandtone.moviescomparator.utils.Constants.WSCLIENT_CONFIG_ERROR_MSG_KEY;

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
	 * Create a OMDb API configuration exception with a native cause.
	 * 
	 * @param cause The native cause to encapsulate
	 */
	public WsClientConfigException(Throwable cause)
	{
		super(WSCLIENT_CONFIG_ERROR_MSG_KEY, cause);
	}	
}