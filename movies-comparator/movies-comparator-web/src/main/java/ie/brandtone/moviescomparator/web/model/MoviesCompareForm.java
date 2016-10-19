package ie.brandtone.moviescomparator.web.model;

import static ie.brandtone.moviescomparator.utils.Commons.ALPHANUMERICAL_PATTERN;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import ie.brandtone.moviescomparator.dao.entity.MovieEntity;

/**
 * The Spring MVC model class for the Movies Comparator form.
 * 
 * @author Marco Pala
 */
public class MoviesCompareForm
{
    /**
     * The first movie title for the comparison.
     */
    @NotEmpty
    @Pattern(regexp = ALPHANUMERICAL_PATTERN)
    String movieTitle1;
    
    /**
     * The second movie title for the comparison.
     */    
    @NotEmpty
    @Pattern(regexp = ALPHANUMERICAL_PATTERN)
    String movieTitle2;
    
    /**
     * The better rated movie.
     */
    MovieEntity winnerMovie;

    /**
     * The message for the result page.
     */
    String message;

    /**
     * The flag for check if a better movie is found.
     */
    boolean winnerFound;
    
    /**
     * The first movie.
     */
    MovieEntity movie1;
    
    /**
     * The second movie.
     */
    MovieEntity movie2;
    
    /**
     * List of cached movies for rendering the local DB table in the View. 
     */
    List<MovieEntity> localMovies;
    
    /**
     * Get the first movie title.
     *
     * @return The first movie title
     */
    public String getMovieTitle1()
    {
        return movieTitle1;
    }
    
    /**
     * Set the first movie title.
     *
     * @param movieTitle1 The first movie title to set
     */
    public void setMovieTitle1(String movieTitle1)
    {
        this.movieTitle1 = movieTitle1;
    }
    
    /**
     * Get the second movie title.
     *
     * @return The second movie title
     */
    public String getMovieTitle2()
    {
        return movieTitle2;
    }
    
    /**
     * Set the second movie title.
     *
     * @param movieTitle2 The second movie title to set
     */
    public void setMovieTitle2(String movieTitle2)
    {
        this.movieTitle2 = movieTitle2;
    }    

    /**
     * Get the winner movie.
     *
     * @return The winner movie
     */
    public MovieEntity getWinnerMovie()
    {
        return winnerMovie;
    }

    /**
     * Set the winner movie.
     *
     * @param winnerMovie The winner movie to set
     */
    public void setWinnerMovie(MovieEntity winnerMovie)
    {
        this.winnerMovie = winnerMovie;
    }
    
    /**
     * Get the result page message.
     *
     * @return The result page message
     */
    public String getMessage()
    {
        return message;
    }
    
    /**
     * Set the result page message.
     *
     * @param message The message to set
     */
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    /**
     * Get the winner found flag.
     *
     * @return The winner found flag.
     */
    public boolean isWinnerFound()
    {
        return winnerFound;
    }

    /**
     * Set the winner found flag.
     *
     * @param winnerFound The winner found flag to set
     */
    public void setWinnerFound(boolean winnerFound)
    {
        this.winnerFound = winnerFound;
    }

    /**
     * Get the first movie.
     *
     * @return The first movie
     */
    public MovieEntity getMovie1()
    {
        return movie1;
    }

    /**
     * Set the first movie.
     *
     * @param movie1 The first movie to set
     */
    public void setMovie1(MovieEntity movie1)
    {
        this.movie1 = movie1;
    }

    /**
     * Get the second movie.
     *
     * @return The second movie
     */
    public MovieEntity getMovie2()
    {
        return movie2;
    }

    /**
     * Set the second movie.
     *
     * @param movie2 The second movie to set
     */
    public void setMovie2(MovieEntity movie2)
    {
        this.movie2 = movie2;
    }

    /**
     * Get the local movies list.
     *
     * @return The local movies list.
     */
    public List<MovieEntity> getLocalMovies()
    {
        return localMovies;
    }

    /**
     * Set the local movies list.
     *
     * @param localMovies The local movies list. to set
     */
    public void setLocalMovies(List<MovieEntity> localMovies)
    {
        this.localMovies = localMovies;
    }    
}
