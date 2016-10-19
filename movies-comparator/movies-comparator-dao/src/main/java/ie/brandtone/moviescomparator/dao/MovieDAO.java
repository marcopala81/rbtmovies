package ie.brandtone.moviescomparator.dao;

import java.util.List;

import ie.brandtone.moviescomparator.dao.entity.MovieEntity;

/**
 * The DAO interface with the common DB operation for the MOVIE table.
 * 
 * @author Marco Pala
 * 
 * @version 1.0.0
 */
public interface MovieDAO
{
    /**
     * Insert the given {@link MovieEntity} in the MOVIE table.
     * 
     * @param movie The MovieEntity to insert
     * @return The generated ID
     * 
     * @since v1.0.0
     */
    Integer insertMovie(MovieEntity movie);
    
    /**
     * Update the given {@link MovieEntity} in the MOVIE table.
     * 
     * @param movie The MovieEntity to update
     * 
     * @since v1.0.0
     */
    void updateMovie(MovieEntity movie);
    
    /**
     * Delete the given {@link MovieEntity} in the MOVIE table (by the generated ID).
     * 
     * @param movieId The generated ID of the MovieEntity to delete
     * 
     * @since v1.0.0
     */
    void deleteMovie(Integer movieId);
    
    /**
     * Find the given {@link MovieEntity} in the MOVIE table (by the generated ID).
     * 
     * @param movieId The generated ID of the MovieEntity to find
     * 
     * @return The MovieEntity correspondant to the given ID (or <code>null</code> if no movie can be found).
     * 
     * @since v1.0.0
     */    
    MovieEntity getMovieById(Integer movieId);

    /**
     * Find the given {@link MovieEntity} in the MOVIE table (by the title).
     * 
     * @param movieTitle The title of the MovieEntity to find
     * 
     * @return The MovieEntity correspondant to the given title (or <code>null</code> if no movie can be found).
     * 
     * @since v1.0.0
     */
    MovieEntity getMovieByTitle(String movieTitle);
    
    /**
     * Find all the {@link MovieEntity} in the MOVIE table.
     * 
     * @return The {@link List} with all the movies in the database.
     * 
     * @since v1.0.0
     */
    List<MovieEntity> getAllMovies(); 
}