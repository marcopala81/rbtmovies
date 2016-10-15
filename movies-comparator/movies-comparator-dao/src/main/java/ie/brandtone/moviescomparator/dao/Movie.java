package ie.brandtone.moviescomparator.dao;

import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;
import static ie.brandtone.moviescomparator.utils.Commons.literalOrNa;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.TO_STRING_MOVIE_KEY;

/**
 * Describes a movie with its relevant properties.
 * 
 * @author Marco Pala
 */
public class Movie
{
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
     * Public constructor with the movie's attributes.
     * 
     * @param id The movie ID
     * @param title The movie title
     * @param rating The movie rating
     */
    public Movie(String id, String title, float rating)
    {
        this.id = literalOrNa(id);
        this.title = literalOrNa(title);
        this.rating = rating;
    }

    /**
     * Get the movie ID.
     *
     * @return The ID field
     */
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
     * Get the movie title.
     *
     * @return The title field
     */
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
     * Get the movie rating.
     *
     * @return The rating field
     */
    public float getRating()
    {
        return rating;
    }

    /**
     * Set the movie rating.
     *
     * @param rating The rating to set
     */
    public void setRating(float rating)
    {
        this.rating = rating;
    }

    /**
     * Overrides the superclass method to correctly format movie's infos for logging.
     * 
     * @return The {@link Movie} string representation
     */
    @Override
    public String toString()
    {
        return getMessageFromBundle(TO_STRING_MOVIE_KEY, this.getId(), this.getTitle(), String.valueOf(this.getRating()));
    }
}
