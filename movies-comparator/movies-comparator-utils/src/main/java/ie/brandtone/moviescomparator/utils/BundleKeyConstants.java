package ie.brandtone.moviescomparator.utils;

/**
 * This class holds all the key literals to retrieve messages from the resource boundle.
 * 
 * @author Marco Pala
 */
public final class BundleKeyConstants
{
    //
    // INFO MESSAGE KEYS
    //
    /**
     * The messages bundle filename (without extension).
     */
    public static final String MESSAGES_BUNDLE_NAME = "messagesBundle";

    /**
     * The key to retrieve the method's entering message for logging purposes.
     */
    public static final String ENTERING_MSG_KEY = "logging.info.enteringMethod";

    /**
     * The key to retrieve the method's exiting message for logging purposes.
     */
    public static final String EXITING_MSG_KEY = "logging.info.exitingMethod";

    /**
     * The key to retrieve the method's leaving message (in case of execution interrupted) for logging purposes.
     */
    public static final String LEAVING_MSG_KEY = "logging.info.leavingMethod";

    /**
     * The key to retrieve the requested movie title message.
     */
    public static final String REQUESTED_TITLE_MSG_KEY = "logging.info.requestedTitle";

    /**
     * The key to retrieve the GET request message for the OMDb API webservice.
     */
    public static final String OMDB_API_GET_REQUEST_MSG_KEY = "logging.info.sendingGetRequestToOMDb";

    /**
     * The key to retrieve the response status message from the OMDb API webservice.
     */
    public static final String OMDB_API_RESPONSE_STATUS_MSG_KEY = "logging.info.responseFromOMDb";

    /**
     * The key to retrieve the simplified JSON to Movie conversion message.
     */
    public static final String SIMPLE_JSON_TO_MOVIE_CONVERSION_MSG_KEY = "logging.info.simpleJsonToMovieConversion";
    
    /**
     * The key to retrieve the movie converted succesfully message.
     */
    public static final String JSON_TO_MOVIE_CONVERTED_MSG_KEY = "logging.info.jsonToMovieConverted";
    
    /**
     * The key to retrieve the OMDb to Movie conversion message.
     */
    public static final String OMDB_JSON_TO_MOVIE_CONVERSION_MSG_KEY = "logging.info.omdbJsonToMovieConversion";
    
    /**
     * The key to retrieve the movie found message.
     */
    public static final String MOVIE_FOUND_MSG_KEY = "logging.info.movieFound";
    
    /**
     * The key to mark a Movie as favourite.
     */    
    public static final String MOVIE_MARKED_AS_FAVOURITE_MSG_KEY = "logging.info.movieMarkedAsFavourite";
    
    /**
     * The key to retrieve the comparing movies message.
     */
    public static final String COMPARING_MOVIES_BY_RATING_MSG_KEY = "logging.info.comparingMoviesByRating";

    /**
     * The key to retrieve the checking favourite movie message.
     */
    public static final String CHECKING_FAVOURITE_MOVIE_MSG_KEY = "logging.info.checkingFavouriteMovie";
    
    /**
     * The key to retrieve the no favourite movie message.
     */
    public static final String NO_FAVOURITE_MOVIE_MSG_KEY = "logging.info.noFavouriteMovie";
    
    /**
     * The key to retrieve the favourite movie found message.
     */
    public static final String FAVOURITE_MOVIE_FOUND_MSG_KEY = "logging.info.favouriteMovieFound";
    
    /**
     * The key to retrieve the highest rated movie message.
     */
    public static final String HIGHEST_RATED_MOVIE_MSG_KEY = "logging.info.highestRatedMovie";

    /**
     * The key to retrieve the same rating movies message.
     */
    public static final String SAME_RATING_MOVIES_MSG_KEY = "logging.info.sameRatingMovies";
    
    //
    // ERROR MESSAGE KEYS
    //
    /**
     * The key to retrieve the response status error message.
     */
    public static final String RESPONSE_STATUS_ERROR_MSG_KEY = "logging.error.responseStatusKO";

    /**
     * The key to retrieve the response field error message.
     */
    public static final String OMDB_RESPONSE_FIELD_ERROR_MSG_KEY = "logging.error.responseFieldFalse";

    /**
     * The key to retrieve the file-not-found error message.
     */
    public static final String FILE_NOT_FOUND_ERROR_MSG_KEY = "logging.error.fileNotFound";

    /**
     * The key to retrieve the unexpected title message.
     */
    public static final String UNEXPECTED_TITLE_ERROR_MSG_KEY = "logging.error.unexpectedTitle";

    /**
     * The key to retrieve the generic error message for initialization of the configuration reader.
     */
    public static final String DEFAULT_CONFIG_READER_ERROR_MSG_KEY = "logging.error.configReader";
    
    /**
     * The key to retrieve the generic error message for test configuration issues.
     */
    public static final String TEST_CONFIG_ERROR_MSG_KEY = "logging.error.testConfig";

    /**
     * The key to retrieve the generic error message for webservice client configuration issues.
     */
    public static final String WSCLIENT_CONFIG_ERROR_MSG_KEY = "logging.error.wsClientConfig";

    /**
     * The key to retrieve the generic error message for the webservice client operations.
     */
    public static final String WS_CLIENT_ERROR_MSG_KEY = "logging.error.wsClient";

    /**
     * The key to retrieve the HTTP error message for the webservice client responses.
     */
    public static final String WS_CLIENT_HTTP_ERROR_MSG_KEY = "logging.error.wsClientHttpCode";

    /**
     * The key to retrieve the generic error message for the movie format issues.
     */
    public static final String MOVIE_FORMAT_ERROR_MSG_KEY = "logging.error.badMovieFormat";

    /**
     * The key to retrieve the generic error message in case of movie not found.
     */
    public static final String MOVIE_NOT_FOUND_ERROR_MSG_KEY = "logging.error.movieNotFound";

    /**
     * The key to retrieve the generic error message in case of comparison errors.
     */
    public static final String COMPARATOR_SERVICE_ERROR_MSG_KEY = "logging.error.comparisonFailed";
    
    //
    // DEBUG MESSAGE KEYS
    //
    /**
     * The key to retrieve the Properties file initialization message.
     */
    public static final String PROPERTIES_INIT_MSG_KEY = "logging.debug.propertiesInit";

    /**
     * The key to retrieve the property-reading message.
     */
    public static final String PROPERTY_READING_MSG_KEY = "logging.debug.propertyReading";

    //
    // TO_STRING FORMATTING
    //
    /**
     * The key to retrieve the movie's <code>toString()</code> formatting message.
     */
    public static final String TO_STRING_SIMPLE_JSON_MOVIE_KEY = "toString.simpleJsonMovie";

    /**
     * The key to retrieve the nested cause message (for exception's <code>toString()</code> formatting).
     */
    public static final String TO_STRING_NESTED_CAUSE_KEY = "toString.nestedCause";

    //
    // TESTING
    //
    /**
     * The key to retrieve the running JUnit test name message.
     */
    public static final String RUNNING_JUNIT_TEST_MSG_KEY = "testing.info.runningTest";

    /**
     * The key to retrieve the movie object message for testing purposes.
     */
    public static final String MOVIE_OBJECT_MSG_KEY = "testing.info.movieObject";
    
    /**
     * The key to retrieve the matching values error message for testing purposes.
     */
    public static final String MATCHING_VALUES_ERROR_MSG_KEY = "testing.error.matchingValues";

    /**
     * The key to retrieve the movie title literal for testing purpose.
     */
    public static final String MOVIE_TITLE_LITERAL_KEY = "testing.literals.movieTitle";
    
    /**
     * Private constructor (for Checkstyle violations purposes).
     */
    private BundleKeyConstants()
    {
        // EMPTY BLOCK
    }
}
