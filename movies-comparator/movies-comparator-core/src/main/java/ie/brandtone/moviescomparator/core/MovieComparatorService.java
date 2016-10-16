package ie.brandtone.moviescomparator.core;

import ie.brandtone.moviescomparator.dao.Movie;
import ie.brandtone.moviescomparator.core.exception.MovieComparatorException;

/**
 * Defines the methods to compare two given movies.
 * 
 * @author Marco Pala
 * @version 1.0.0
 */
public interface MovieComparatorService
{
    /**
     * Given two movie titles, compares their rating and return the best one (or <code>null</code> if the movies have the same rating).
     * 
     * @param title1 The first title to compare
     * @param title2 The second title to compare
     * 
     * @return The best movie based on its rating or (<code>null</code> if the movies have the same rating)
     * 
     * @throws MovieComparatorException in case of any issue during the movie comparison
     * 
     * @since v1.0.0
     */
    Movie compareMoviesByRating(String title1, String title2) throws MovieComparatorException;
    
    /**
     * Given two {@link Movie} objects, compares their rating and return the best one (or <code>null</code> if the movies have the same rating).
     * 
     * @param movie1 The first Movie to compare
     * @param movie2 The second Movie to compare
     * 
     * @return The best Movie based on its rating or (<code>null</code> if the movies have the same rating)
     * 
     * @throws MovieComparatorException in case of any issue during the movie comparison
     * 
     * @since v1.0.0
     */
    Movie compareMoviesByRating(Movie movie1, Movie movie2) throws MovieComparatorException;    
}
