package ie.brandtone.moviescomparator.utils;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.ENTERING_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.EXITING_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.LEAVING_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MATCHING_VALUES_ERROR_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.PROPERTIES_INIT_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.PROPERTY_READING_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MESSAGES_BUNDLE_NAME;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.TO_STRING_NESTED_CAUSE_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.FILE_NOT_FOUND_ERROR_MSG_KEY;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * This class exposes all the utility methods that can be reused anywhere in the project.
 * 
 * @author Marco Pala
 * 
 * @version 1.0.0
 */
public final class Commons
{
    /**
     * Initialize the {@link ResourceBundle} for messaging purposes.
     */
    private static final ResourceBundle MESSAGES_BUNDLE = ResourceBundle.getBundle(MESSAGES_BUNDLE_NAME, new Locale("en", "IE"));

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
        String unformattedMsg = MESSAGES_BUNDLE.getString(key);

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
     * Get the {@link Properties} initialization message formatted with the filename for debugging purposes.
     * 
     * @param filename The filename to trace
     * 
     * @return The formatted Properties initialization message with the filename
     * 
     * @since v1.0.0
     */
    public static String getPropertiesInitMsg(String filename)
    {
        return getMessageFromBundle(PROPERTIES_INIT_MSG_KEY, filename);
    }

    /**
     * Get the property-reading message formatted with the key/value pair for debugging purposes.
     * 
     * @param key The key of the property to trace
     * @param value The value of the property to trace
     * 
     * @return The formatted property-reading message with the key/value pair
     * 
     * @since v1.0.0
     */
    public static String getPropertyReadingMsg(String key, String value)
    {
        return getMessageFromBundle(PROPERTY_READING_MSG_KEY, key, value);
    }

    /**
     * Given a {@link String} literal, this method checks if it is empty (or null) and eventually returns the {@link Commons#N_A} value; otherwise returns the literal itself.
     * 
     * @param literal The literal to check
     * 
     * @return The {@link Commons#N_A} value (if <code>literal</code> is null or empty) or the literal itself
     * 
     * @since v1.0.0
     */
    public static String literalOrNa(String literal)
    {
        return (literal == null || literal.isEmpty()) ? N_A : literal;
    }

    /**
     * Common routine to load a {@link Properties} file given the caller and a filename.
     * 
     * @param p Properties file to load
     * @param clazz The caller class
     * @param configFilename The Properties filename
     * 
     * @return The Properties file with the loaded key-value map
     * 
     * @throws IOException in case of any I/O issue
     * 
     * @since v1.0.0
     */
    public static Properties loadProperties(Properties p, Class<?> clazz, String configFilename) throws IOException
    {
        InputStream is = clazz.getClassLoader().getResourceAsStream(configFilename);

        if (is != null)
        {
            p.load(is);
            is.close();
        }
        else
        {
            throw new FileNotFoundException(getMessageFromBundle(FILE_NOT_FOUND_ERROR_MSG_KEY, configFilename));
        }

        return p;
    }

    /**
     * Common method to append the first nested cause message (if any) to the original exception message.
     * 
     * @param e The exception to parse
     * 
     * @return The exception string representation plus the nested cause string representation (if any)
     * 
     * @since v1.0.0
     */
    public static String nestedExceptionToString(Exception e)
    {
        StringBuilder traceMsg = new StringBuilder();
        Throwable nestedCause = e.getCause();

        if (nestedCause != null)
        {
            traceMsg.append(getMessageFromBundle(TO_STRING_NESTED_CAUSE_KEY, e.toString(), nestedCause.toString()));
        }
        else
        {
            traceMsg.append(e.toString());
        }

        return traceMsg.toString();
    }
    
    /**
     * Private constructor (for Checkstyle violations purposes).
     */
    private Commons()
    {
        // EMPTY BLOCK
    }    
}
