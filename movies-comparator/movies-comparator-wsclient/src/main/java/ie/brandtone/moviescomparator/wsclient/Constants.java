package ie.brandtone.moviescomparator.wsclient;

/**
 * This class holds all the relevant literals/constants of the project.
 * <br>
 * TODO: move to the proper module/package...
 * 
 * @author Marco Pala
 */
public final class Constants
{
	//
	// LITERALS
	//
	/**
	 * The <code>N/A</code> literal (in case of not assigned {@link String} values).
	 */
	public static final String N_A = "N/A";

	/**
	 * The space (<code>" "</code>) literal.
	 */
	public static final String SPACE = " ";
	
	/**
	 * Generic literal for including a nested exception trace (fill '%s' parameter).
	 */
	public static final String NESTED_CAUSE_MSG = "Nested exception >>> %s";
	
	//
	// OMDb API
	//
	/**
	 * The OMDb API URL.
	 */
	public static final String OMDB_API_URL = "http://www.omdbapi.com/";
	
	/**
	 * The key to put the title parameter on a OMDb API request.
	 */
	public static final String OMDB_TITLE_PARAM = "t";

	/**
	 * The value of the OMDb API <code>Response</code> field in case of movie found.
	 */	
	public static final String OMDB_RESPONSE_FOUND = "True";
	
	/**
	 * The key to retrieve the OMBd API ID field.
	 */
	public static final String OMDB_ID_FIELD =  "imdbID";

	/**
	 * The key to retrieve the OMBd API title field.
	 */
	public static final String OMDB_TITLE_FIELD = "Title";

	/**
	 * The key to retrieve the OMBd API rating field.
	 */	
	public static final String OMDB_RATING_FIELD = "imdbRating";

	/**
	 * The key to retrieve the OMBd API response field (for testing purposes).
	 */	
	public static final String OMDB_RESPONSE_FIELD = "Response";
		
	//
	// ERROR MESSAGES
	//
	/**
	 * Generic error message for the webservice client operations.
	 */
	public static final String WS_CLIENT_ERROR_MSG = "An error occurred while processing the webservice client.";

	/**
	 * Optional error message for the webservice client response ('%s' will be filled with the proper HTTP return code).
	 */
	public static final String HTTP_ERROR_MSG = "See HTTP error code %s.";
	
	/**
	 * Generic error message for the movie format issues.
	 */
	public static final String MOVIE_FORMAT_ERROR_MSG = "Unexpected bad movie format.";
	
	/**
	 * Generic error message in case of movie not found ('%s' will be filled with the requested movie title).
	 */
	public static final String MOVIE_NOT_FOUND_ERROR_MSG = "The requested movie ('%s') cannot be found.";
	
	//
	// TEST LITERALS
	//
	/**
	 * Generic error message for testing matching values ('%s' will be filled with the tested variable).
	 */	
	public static final String MATCHING_VALUES_ERROR_MSG = "Bad value on %s >>>";

	/**
	 * Generic error message for testing matching exception types ('%s' will be filled with the tested variable).
	 */	
	public static final String MATCHING_EXCEPTION_TYPE_ERROR_MSG = "Unexpected exception type >>>";
		
	/**
	 * The movie title literal for testing purpose.
	 */
	public static final String MOVIE_TITLE = "movie title";	
}
