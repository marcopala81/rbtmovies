package ie.brandtone.moviescomparator.dao;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.JSON_TO_MOVIE_CONVERTED_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_OBJECT_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.SIMPLE_JSON_TO_MOVIE_CONVERSION_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getEnteringMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getExitingMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getLeavingMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;
import static ie.brandtone.moviescomparator.utils.Commons.ID_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.RATING_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.TITLE_KEY;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import ie.brandtone.moviescomparator.dao.entity.MovieEntity;
import ie.brandtone.moviescomparator.dao.exception.BadMovieFormatException;
import ie.brandtone.moviescomparator.utils.BaseMoviesComparatorTest;
import ie.brandtone.moviescomparator.utils.exceptions.TestConfigException;

/**
 * Creates general purpose {@link Movie} objects (adhere to the <i>AbstractFactory</i> pattern).
 * 
 * @author Marco Pala
 * 
 * @version 1.0.0
 */
public abstract class AbstractMovieFactory
{
    /**
     * The Apache Log4j logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AbstractMovieFactory.class);
    
    /**
     * Creates a {@link MovieEntity} object with the movie's attributes.
     * 
     * @param imdbId The movie imdbId
     * @param title The movie title
     * @param rating The movie rating
     * 
     * @return A new MovieEntity object with filled attributes
     * @since v1.0.0
     */
    public static MovieEntity newMovieEntity(String imdbId, String title, String rating)
    {
        MovieEntity movie = new MovieEntity(imdbId, title, Float.parseFloat(rating));
        LOGGER.debug(getMessageFromBundle(MOVIE_OBJECT_MSG_KEY, movie.toString()));
        
        return movie;
    }  
        
    /**
     * Creates a {@link MovieEntity} object starting from its {@link JSONObject} representation.
     * 
     * @param jsonMovie The JSONObject representation for the movie to create
     * 
     * @return The MovieEntity object converted from its JSONObject representation 
     * 
     * @throws BadMovieFormatException in case of a bad JSONObject input format or conversion issues
     * 
     * @since v1.0.0
     */
    public static MovieEntity getMovieFromJson(JSONObject jsonMovie) throws BadMovieFormatException
    {
        String methodName = "getMovieFromJson(JSONObject)";
        LOGGER.info(getEnteringMessage(methodName));

        MovieEntity movie = null;
        try
        {
            LOGGER.info(getMessageFromBundle(SIMPLE_JSON_TO_MOVIE_CONVERSION_MSG_KEY));
            // Get relevant movie's properties
            movie = newMovieEntity(jsonMovie.getString(ID_KEY), jsonMovie.getString(TITLE_KEY), jsonMovie.getString(RATING_KEY));
        }
        catch (JSONException | NumberFormatException e)
        {
            // In case of any issue on parsing movie's properties
            LOGGER.error(e);
            LOGGER.info(getLeavingMessage(methodName));
            throw new BadMovieFormatException(e);
        }

        LOGGER.info(getMessageFromBundle(JSON_TO_MOVIE_CONVERTED_MSG_KEY));
        LOGGER.info(getExitingMessage(methodName));
        return movie;
    }
    
    /**
     * Creates a {@link MovieEntity} object starting from its JSON {@link String} representation.
     * 
     * @param jsonStringMovie The JSON String representation for the movie to create
     * 
     * @return The MovieEntity object converted from its String representation 
     * 
     * @throws BadMovieFormatException in case of a bad input format or conversion issues
     * 
     * @since v1.0.0
     */
    public static MovieEntity getMovieFromJson(String jsonStringMovie) throws BadMovieFormatException
    {
        String methodName = "getMovieFromJson(String)";
        LOGGER.info(getEnteringMessage(methodName));

        // Convert the JSON String representation of the movie to a JSON java object
        MovieEntity movie = null;
        JSONObject jsonMovie = null;
        
        try
        {
            jsonMovie = new JSONObject(jsonStringMovie);
            movie = getMovieFromJson(jsonMovie);
        }
        catch (JSONException je)
        {
            // In case of any issue on converting String to JSON
            LOGGER.error(je);
            LOGGER.info(getLeavingMessage(methodName));
            throw new BadMovieFormatException(je);            
        }
        
        LOGGER.info(getExitingMessage(methodName));
        return movie;
    }
    
    /**
     * Creates a {@link MovieEntity} object starting from its attributes in a test configuration file.
     * 
     * @param testReader The reader for the given test
     * @param imdbIdKey The key to retrieve the movie imdbId
     * @param titleKey The key to retrieve the movie title
     * @param ratingKey The key to retrieve the movie rating
     * 
     * @return A new MovieEntity object with filled attributes
     * 
     * @throws TestConfigException in case of any configuration issue for the test reader
     * 
     * @since v1.0.0
     */
    public static MovieEntity getMovieFromTestConfig(BaseMoviesComparatorTest testReader, String imdbIdKey, String titleKey, String ratingKey) throws TestConfigException
    {
        String imdbId = testReader.getTestProperty(imdbIdKey);
        String title = testReader.getTestProperty(titleKey);
        String rating = testReader.getTestProperty(ratingKey);
                
        MovieEntity movie = newMovieEntity(imdbId, title, rating);        
        return movie;
    }    
}
