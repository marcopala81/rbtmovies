package ie.brandtone.moviescomparator.web;

import java.util.List;

import ie.brandtone.moviescomparator.dao.entity.MovieEntity;
import ie.brandtone.moviescomparator.web.exception.MvcFacadeException;
import ie.brandtone.moviescomparator.web.model.MoviesCompareForm;

/**
 * Defines the methods of the Web Module facade to expose to the MVC (adhere to the <i>Facade</i> pattern).
 * 
 * @author Marco Pala
 * @version 1.0.1
 */
public interface MoviesMvcFacade
{    
    /**
     * Given the Model with the two {@link MovieEntity} objects, prepares the comparison results and fills the Model ({@link MoviesCompareForm}.
     * 
     * @param model The form filled with the two given movie titles to compare
     * 
     * @return The Model MoviesCompareForm filled with the comparison results 
     * 
     * @throws MvcFacadeException in case of any issue during the comparison
     * 
     * @since v1.0.0
     */    
    MoviesCompareForm prepareComparisonResults(MoviesCompareForm model) throws MvcFacadeException;

    /**
     * Given the Model with the two {@link MovieEntity} objects, checks for user modification (on rating or favourite flag) and persists movies.
     * <br>
     * <ul>
     * <b>PATCH 1.0.1</b> - Solved the issue with the favourite mark:
     * <li>If a movie is marked as favourite the old favourite in the local movies DB loses its mark</li>
     * <li>(now throws a {@link MvcFacadeException} in case of any local movies DB issue)</li>
     * <li>The changed and the favourite flags are now handled separately</li>
     * </ul>
     * 
     * @param model The form filled with the two given movies to check
     *
     * @return The Model MoviesCompareForm filled with the modification message 
     *  
     * @throws MvcFacadeException in case of any local movies DB issue
     * 
     * @since v1.0.0
     */
    MoviesCompareForm checkForLocalModifications(MoviesCompareForm model) throws MvcFacadeException;

    /**
     * Given the Model with the two {@link MovieEntity} objects saves them.
     * 
     * @param model The form filled with the two given movies to save
     * 
     * @throws MvcFacadeException in case of any database issue
     * 
     * @since v1.0.0
     */
    void saveMovies(MoviesCompareForm model) throws MvcFacadeException;
    
    /**
     * Returns the complete list of cached movies from the local DB (both changed/unchanged).
     * 
     * @return The complete list of cached movies from the local DB
     * 
     * @throws MvcFacadeException in case of any database issue
     * 
     * @since v1.0.0
     */
    List<MovieEntity> getCachedMovies() throws MvcFacadeException;
}
