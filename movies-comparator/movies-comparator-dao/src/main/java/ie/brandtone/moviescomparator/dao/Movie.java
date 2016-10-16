package ie.brandtone.moviescomparator.dao;

/**
 * Describes a movie with the relevant getter methods and operations.
 * 
 * @author Marco Pala
 * 
 * @version 1.0.0
 */
public interface Movie
{
    /**
     * The generic JSON ID key.
     */
    static final String ID_KEY = "ID";
    
    /**
     * The generic JSON title key.
     */
    static final String TITLE_KEY = "Title";

    /**
     * The generic JSON rating key.
     */
    static final String RATING_KEY = "Rating";

    /**
     * The generic JSON favourite key.
     */
    static final String FAVOURITE_KEY = "Favourite";
    
    /**
     * Get the movie ID.
     *
     * @return The ID field
     * 
     * @since v1.0.0
     */
    String getId();

    /**
     * Get the movie title.
     *
     * @return The title field
     * 
     * @since v1.0.0
     */
    String getTitle();

    /**
     * Get the movie rating.
     *
     * @return The rating field
     * 
     * @since v1.0.0
     */
    float getRating();

    /**
     * Get the movie favourite flag.
     *
     * @return The favourite flag
     * 
     * @since v1.0.0
     */
    boolean isFavourite();
    
    /**
     * Mark the movie as favourite.
     * 
     * @since v1.0.0
     */
    void markAsFavourite();
    
    /**
     * Format the {@link Movie}'s information as a JSON string for logging purposes.
     * 
     * @return The Movie string representation
     * 
     * @since v1.0.0
     */
    String toString();
}
