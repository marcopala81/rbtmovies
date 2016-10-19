package ie.brandtone.moviescomparator.core;

import ie.brandtone.moviescomparator.core.exception.MovieComparatorException;
import ie.brandtone.moviescomparator.dao.entity.MovieEntity;

/**
 * Defines the methods to compare two given movies.
 * 
 * @author Marco Pala
 * @version 1.0.0
 */
public interface MovieComparatorService
{    
    /**
     * Given two {@link MovieEntity} objects, compares their rating and return the best one (or <code>null</code> if the movies have the same rating).
     * 
     * @param movie1 The first MovieEntity to compare
     * @param movie2 The second MovieEntity to compare
     * 
     * @return The best MovieEntity based on its rating or (<code>null</code> if the movies have the same rating)
     * 
     * @throws MovieComparatorException in case of any issue during the movie comparison
     * 
     * @since v1.0.0
     */
    MovieEntity compareMoviesByRating(MovieEntity movie1, MovieEntity movie2) throws MovieComparatorException;    
}
