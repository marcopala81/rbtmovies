package ie.brandtone.moviescomparator.utils;

import static ie.brandtone.moviescomparator.utils.Constants.ENTERING_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Constants.EXITING_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Constants.LEAVING_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Constants.MATCHING_VALUES_ERROR_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Constants.MESSAGES_BUNDLE_NAME;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class exposes all the utility methods that can be reused anywhere in the project.
 * 
 * @author Marco Pala
 * @version 1.0.0
 */
public final class Commons
{
	/**
	 * Initialize the {@link ResourceBundle} for messaging purposes.
	 */
	private final static ResourceBundle messagesBundle = ResourceBundle.getBundle(MESSAGES_BUNDLE_NAME, new Locale("en","IE"));
	
	//
	// COMMON LITERALS
	//	
	/**
	 * The <code>N/A</code> literal (in case of not assigned {@link String} values).
	 */
	public static final String N_A = "N/A";
	
	/**
	 * Get a formatted message from the messages bundle (matching the given key).
	 * 
	 * @param key The key to retrieve the message
	 * @param args The variable-length argument list to format the message
	 * 
	 * @return The formatted message with the given arguments
	 * 
	 * @since v1.0.0
	 */
	public static String getMessageFromBundle(String key, String... args)
	{
		String unformattedMsg = messagesBundle.getString(key);
		
		return (args == null) ? unformattedMsg : MessageFormat.format(unformattedMsg, (Object[]) args);
	}
	
	/**
	 * Get the entering message formatted with method's name.
	 * 
	 * @param methodName The method's name
	 * 
	 * @return The formatted entering message with method's name
	 * 
	 * @since v1.0.0
	 */
	public static String getEnteringMessage(String methodName)
	{
		return getMessageFromBundle(ENTERING_MSG_KEY, methodName);
	}
	
	/**
	 * Get the exiting message formatted with method's name.
	 * 
	 * @param methodName The method's name
	 * 
	 * @return The formatted exiting message with method's name
	 * 
	 * @since v1.0.0
	 */
	public static String getExitingMessage(String methodName)
	{
		return getMessageFromBundle(EXITING_MSG_KEY, methodName);
	}
	
	/**
	 * Get the leaving message formatted with method's name.
	 * 
	 * @param methodName The method's name
	 * 
	 * @return The formatted leaving message with method's name
	 * 
	 * @since v1.0.0
	 */
	public static String getLeavingMessage(String methodName)
	{
		return getMessageFromBundle(LEAVING_MSG_KEY, methodName);
	}

	/**
	 * Get the matching values error message key formatted with field literal for testing purposes.
	 * 
	 * @param literalKey The key to retrieve the field literal
	 * 
	 * @return The formatted error message with field literal
	 * 
	 * @since v1.0.0
	 */
	public static String getMatchingValuesErrorMsg(String literalKey)
	{
		return getMessageFromBundle(MATCHING_VALUES_ERROR_MSG_KEY, getMessageFromBundle(literalKey));
	}
	
	/**
	 * Given a {@link String} literal, this method checks if it is empty (or null) and eventually returns the {@link Constants#N_A} value;
	 * otherwise returns the literal itself.
	 * 
	 * @param literal The literal to check
	 * 
	 * @return The {@link Constants#N_A} value (if <code>literal</code> is null or empty) or the literal itself
	 * 
	 * @since v1.0.0
	 */
	public static String literalOrNa(String literal)
	{
		return (literal == null || literal.isEmpty()) ? N_A : literal;
	}
}
