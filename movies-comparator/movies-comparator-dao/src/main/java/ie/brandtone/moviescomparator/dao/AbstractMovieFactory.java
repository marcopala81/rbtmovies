package ie.brandtone.moviescomparator.dao;

import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.JSON_TO_MOVIE_CONVERTED_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_OBJECT_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.SIMPLE_JSON_TO_MOVIE_CONVERSION_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.getEnteringMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getExitingMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getLeavingMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import ie.brandtone.moviescomparator.dao.entity.MovieEntity;
import ie.brandtone.moviescomparator.dao.exception.BadMovieFormatException;
import ie.brandtone.moviescomparator.dao.impl.MovieImpl;

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
     * The generic JSON ID key.
     */
    public static final String ID_KEY = "ID";
    
    /**
     * The generic JSON title key.
     */
    public static final String TITLE_KEY = "Title";

    /**
     * The generic JSON rating key.
     */
    public static final String RATING_KEY = "Rating";

    /**
     * The generic JSON favourite key.
     */
    public static final String FAVOURITE_KEY = "Favourite";  
    
    /**
     * The Apache Log4j logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AbstractMovieFactory.class);
    
    /**
     * Creates a {@link Movie} with the default attributes.
     * 
     * @return The Movie object with the default attributes
     * 
     * @since v1.0.0
     */
    public static Movie defaultMovie()
    {
        Movie movie = (Movie) new MovieImpl();
        LOGGER.debug(getMessageFromBundle(MOVIE_OBJECT_MSG_KEY, movie.toString()));
        
        return movie;
    }
    
    /**
     * Creates a {@link Movie} object with the movie's attributes.
     * 
     * @param id The movie ID
     * @param title The movie title
     * @param rating The movie rating
     * 
     * @return A new Movie object with filled attributes
     * @since v1.0.0
     */
    public static Movie newMovie(String id, String title, float rating)
    {
        Movie movie = (Movie) new MovieImpl(id, title, rating);
        LOGGER.debug(getMessageFromBundle(MOVIE_OBJECT_MSG_KEY, movie.toString()));
        
        return movie;
    }
    
    /**
     * Creates a {@link MovieEntity} object with the movie's attributes.
     * 
     * @param id The movie ID
     * @param title The movie title
     * @param rating The movie rating
     * 
     * @return A new MovieEntity object with filled attributes
     * @since v1.0.0
     */
    public static MovieEntity newMovieEntity(String id, String title, float rating)
    {
        MovieEntity movieEntity = new MovieEntity(id, title, rating);
        LOGGER.debug(getMessageFromBundle(MOVIE_OBJECT_MSG_KEY, movieEntity.toString()));
        
        return movieEntity;
    }
    
    /**
     * Creates a {@link MovieEntity} object from a {@link Movie} object (by copying fields).
     * 
     * @param movie The Movie to copy
     * 
     * @return A new MovieEntity object equivalent to the Movie object
     * 
     * @since v1.0.0
     */
    public static MovieEntity getMovieEntityFromMovie(Movie movie)
    {
        MovieEntity movieEntity = newMovieEntity(movie.getId(), movie.getTitle(), movie.getRating());
        LOGGER.debug(getMessageFromBundle(MOVIE_OBJECT_MSG_KEY, movieEntity.toString()));
        
        return movieEntity;
    }    
    
    /**
     * Creates a {@link Movie} object starting from its {@link JSONObject} representation.
     * 
     * @param jsonMovie The JSONObject representation for the movie to create
     * 
     * @return The Movie object converted from its JSONObject representation 
     * 
     * @throws BadMovieFormatException in case of a bad JSONObject input format or conversion issues
     * 
     * @since v1.0.0
     */
    public static Movie getMovieFromJson(JSONObject jsonMovie) throws BadMovieFormatException
    {
        String methodName = "getMovieFromJson(JSONObject)";
        LOGGER.info(getEnteringMessage(methodName));

        Movie movie = null;
        try
        {
            LOGGER.info(getMessageFromBundle(SIMPLE_JSON_TO_MOVIE_CONVERSION_MSG_KEY));
            // Get relevant movie's properties
            movie = newMovie(jsonMovie.getString(ID_KEY),
                                jsonMovie.getString(TITLE_KEY),
                                    Float.parseFloat(jsonMovie.getString(RATING_KEY)));
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
     * Creates a {@link Movie} object starting from its JSON {@link String} representation.
     * 
     * @param jsonStringMovie The JSON String representation for the movie to create
     * 
     * @return The Movie object converted from its String representation 
     * 
     * @throws BadMovieFormatException in case of a bad input format or conversion issues
     * 
     * @since v1.0.0
     */
    public static Movie getMovieFromJson(String jsonStringMovie) throws BadMovieFormatException
    {
        String methodName = "getMovieFromJson(String)";
        LOGGER.info(getEnteringMessage(methodName));

        // Convert the JSON String representation of the movie to a JSON java object
        JSONObject jsonMovie = null;
        
        try
        {
            jsonMovie = new JSONObject(jsonStringMovie);
        }
        catch (JSONException je)
        {
            // In case of any issue on converting String to JSON
            LOGGER.error(je);
            LOGGER.info(getLeavingMessage(methodName));
            throw new BadMovieFormatException(je);            
        }
        
        LOGGER.info(getExitingMessage(methodName));
        return getMovieFromJson(jsonMovie);
    }    
}
