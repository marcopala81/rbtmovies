package ie.brandtone.moviescomparator.utils;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.DEFAULT_CONFIG_READER_ERROR_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;
import static ie.brandtone.moviescomparator.utils.Commons.isNullString;

/**
 * Used for reporting an exception related to the {@link AbstractConfigReader}.
 * 
 * @author Marco Pala
 */
public class ConfigException extends Exception
{
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -9039208190362722373L;
    
    /**
     * Create a configuration exception with a native cause and an (optional) error message key.
     * 
     * @param cause The native cause to encapsulate
     * @param errorMsgKey The optional error message key (default is {@link BundleKeyConstants#DEFAULT_CONFIG_READER_ERROR_MSG_KEY})
     */
    public ConfigException(Throwable cause, String...errorMsgKey)
    {
        super(getMessageFromBundle(isNullString((String) errorMsgKey[0]) ? DEFAULT_CONFIG_READER_ERROR_MSG_KEY
                                                                         : (String) errorMsgKey[0]),
              cause);
    }
}
