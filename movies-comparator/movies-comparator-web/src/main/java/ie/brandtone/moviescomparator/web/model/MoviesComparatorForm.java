package ie.brandtone.moviescomparator.web.model;

import static ie.brandtone.moviescomparator.utils.Commons.ALPHANUMERICAL_PATTERN;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The Spring MVC model class for the Movies Comparator form.
 * 
 * @author Marco Pala
 */
public class MoviesComparatorForm
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
     * The better rated movie title.
     */
    String winnerTitle;

    /**
     * The message for the result page.
     */
    String message;

    /**
     * The flag for check if a better movie is found.
     */
    boolean winnerFound;
    
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
     * Get the better rated movie title.
     *
     * @return The better rated movie title
     */
    public String getWinnerTitle()
    {
        return winnerTitle;
    }
    
    /**
     * Set the better rated movie title.
     *
     * @param winnerTitle The better rated movie title to set
     */
    public void setWinnerTitle(String winnerTitle)
    {
        this.winnerTitle = winnerTitle;
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
}
