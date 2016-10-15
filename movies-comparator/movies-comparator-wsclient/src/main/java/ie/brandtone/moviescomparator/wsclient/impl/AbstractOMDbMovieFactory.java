package ie.brandtone.moviescomparator.wsclient.impl;

import static ie.brandtone.moviescomparator.utils.Commons.getEnteringMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getExitingMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getLeavingMessage;
import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.OMDB_JSON_TO_MOVIE_CONVERSION_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.OMDB_JSON_TO_MOVIE_CONVERTED_MSG_KEY;
import static ie.brandtone.moviescomparator.wsclient.impl.OMDbApiConfig.OMDB_ID_FIELD_KEY;
import static ie.brandtone.moviescomparator.wsclient.impl.OMDbApiConfig.OMDB_TITLE_FIELD_KEY;
import static ie.brandtone.moviescomparator.wsclient.impl.OMDbApiConfig.OMDB_RATING_FIELD_KEY;
import static ie.brandtone.moviescomparator.wsclient.impl.OMDbApiConfig.getOMDbApiProperty;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import ie.brandtone.moviescomparator.dao.Movie;
import ie.brandtone.moviescomparator.dao.exception.BadMovieFormatException;
import ie.brandtone.moviescomparator.wsclient.exception.WsClientConfigException;

/**
 * Creates {@link Movie} objects based on OMDb API movie's information (adhere to the <i>AbstractFactory</i> pattern).
 * 
 * @author Marco Pala
 * 
 * @version 1.0.0
 */
public abstract class AbstractOMDbMovieFactory
{
    /**
     * The Apache Log4j logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AbstractOMDbMovieFactory.class);

    /**
     * Creates a {@link Movie} object starting from its JSON object representation.
     * 
     * @param omdbMovie The JSON object representation of the movie to create
     * 
     * @return The {@link Movie} object converted from its JSON representation 
     * 
     * @throws BadMovieFormatException in case of a bad JSON input format or conversion issues
     * 
     * @since v1.0.0
     */
    public static Movie movieFromJson(JSONObject omdbMovie) throws BadMovieFormatException
    {
        String methodName = "movieFromJson";
        LOGGER.info(getEnteringMessage(methodName));

        Movie movie = null;
        String idKey = null;
        String titleKey = null;
        String ratingKey = null;

        try
        {
            LOGGER.info(getMessageFromBundle(OMDB_JSON_TO_MOVIE_CONVERSION_MSG_KEY));
            // Prepare field keys to retrieve
            idKey = getOMDbApiProperty(OMDB_ID_FIELD_KEY);
            titleKey = getOMDbApiProperty(OMDB_TITLE_FIELD_KEY);
            ratingKey = getOMDbApiProperty(OMDB_RATING_FIELD_KEY);

            // Get relevant movie's properties
            movie = new Movie(omdbMovie.getString(idKey), omdbMovie.getString(titleKey), Float.parseFloat(omdbMovie.getString(ratingKey)));
        }
        catch (WsClientConfigException | JSONException | NumberFormatException e)
        {
            // In case of any issue on getting movie's properties
            LOGGER.error(e);
            LOGGER.info(getLeavingMessage(methodName));
            throw new BadMovieFormatException(e);
        }

        LOGGER.info(getMessageFromBundle(OMDB_JSON_TO_MOVIE_CONVERTED_MSG_KEY));
        LOGGER.info(getExitingMessage(methodName));
        return movie;
    }
}
