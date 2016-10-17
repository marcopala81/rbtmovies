package ie.brandtone.moviescomparator.dao.impl;

import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;
import static ie.brandtone.moviescomparator.dao.AbstractMovieFactory.ID_KEY; 
import static ie.brandtone.moviescomparator.dao.AbstractMovieFactory.TITLE_KEY;
import static ie.brandtone.moviescomparator.dao.AbstractMovieFactory.RATING_KEY;
import static ie.brandtone.moviescomparator.dao.AbstractMovieFactory.FAVOURITE_KEY;

import org.apache.log4j.Logger;

import ie.brandtone.moviescomparator.dao.Movie;

import static ie.brandtone.moviescomparator.utils.Commons.N_A;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.TO_STRING_SIMPLE_JSON_MOVIE_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_MARKED_AS_FAVOURITE_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_SCORE_UPDATED_MSG_KEY;

/**
 * Implements a {@link Movie} with its relevant properties and operations.
 * 
 * @author Marco Pala
 */
public class MovieImpl implements Movie
{
    /**
     * The Apache Log4j logger.
     */
    private static final Logger LOGGER = Logger.getLogger(MovieImpl.class);
    
    /**
     * The movie ID.
     */
    private String id;

    /**
     * The movie title.
     */
    private String title;

    /**
     * The movie rating.
     */
    private float rating;

    /**
     * The movie favourite flag.
     */
    private boolean favourite;
    
    /**
     * Constructor with default initialization fields.
     */
    public MovieImpl()
    {
        this.id = N_A;
        this.title = N_A;
        this.rating = 0.0f;
        this.favourite = false;
    }
    
    /**
     * Constructor with specified fields.
     * 
     * @param id The movie ID
     * @param title The movie title
     * @param rating The movie rating
     */
    public MovieImpl(String id, String title, float rating)
    {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.favourite = false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getId()
    {
        return id;
    }

    /**
     * Set the movie ID.
     *
     * @param id The ID to set
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle()
    {
        return title;
    }

    /**
     * Set the movie title.
     *
     * @param title The title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getRating()
    {
        return rating;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRating(float rating)
    {
        LOGGER.info(getMessageFromBundle(MOVIE_SCORE_UPDATED_MSG_KEY, this.getTitle(), String.valueOf(this.getRating()), String.valueOf(rating)));
        this.rating = rating;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFavourite()
    {
        return favourite;
    }

    /**
     * Set the movie favourite flag.
     *
     * @param favourite The favourite flag to set
     */
    public void setFavourite(boolean favourite)
    {
        this.favourite = favourite;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void markAsFavourite()
    {
        this.setFavourite(true);
        LOGGER.info(getMessageFromBundle(MOVIE_MARKED_AS_FAVOURITE_MSG_KEY, this.getTitle()));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return getMessageFromBundle(TO_STRING_SIMPLE_JSON_MOVIE_KEY,
                                    ID_KEY, this.getId(),
                                    TITLE_KEY, this.getTitle(),
                                    RATING_KEY, String.valueOf(this.getRating()),
                                    FAVOURITE_KEY, String.valueOf(this.isFavourite()));
    }   
}