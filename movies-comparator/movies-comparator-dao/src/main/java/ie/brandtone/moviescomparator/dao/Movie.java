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
     * Set the movie rating (for modifing local results).
     *
     * @param rating The new rating to set
     * 
     * @since v1.0.0
     */
    void setRating(float rating);
    
    /**
     * Get the movie favourite flag.
     *
     * @return The favourite flag
     * 
     * @since v1.0.0
     */
    boolean isFavourite();
    
    /**
     * Mark the movie as favourite (for pre-cooking results).
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
