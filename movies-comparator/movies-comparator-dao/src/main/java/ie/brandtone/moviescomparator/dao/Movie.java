package ie.brandtone.moviescomparator.dao;

/**
 * Describes a movie with the relevant getter methods and operations (as for the specifications for the Movies Comparator project).
 * 
 * @author Marco Pala
 * 
 * @version 1.0.0
 */
public interface Movie
{    
    /**
     * Get the movie IMDb ID.
     *
     * @return The movie IMDb ID field
     * 
     * @since v1.0.0
     */
    String getImdbId();

    /**
     * Get the movie title.
     *
     * @return The movie title field
     * 
     * @since v1.0.0
     */
    String getTitle();

    /**
     * Get the movie rating.
     *
     * @return The movie rating field
     * 
     * @since v1.0.0
     */
    Float getRating();

    /**
     * Set the movie rating (for modifing local results).
     *
     * @param rating The new rating to set
     * 
     * @since v1.0.0
     */
    void setRating(Float rating);
    
    /**
     * Get the movie favourite flag.
     *
     * @return The favourite flag
     * 
     * @since v1.0.0
     */
    Boolean getFavourite();
    
    /**
     * Set the movie favourite flag (for pre-cooking results).
     *
     * @param favourite The favourite flag to set
     * 
     * @since v1.0.0
     */
    void setFavourite(Boolean favourite);
    
    /**
     * Format the {@link Movie}'s information as a JSON string for logging purposes.
     * 
     * @return The Movie string representation
     * 
     * @since v1.0.0
     */
    String toString();
}
