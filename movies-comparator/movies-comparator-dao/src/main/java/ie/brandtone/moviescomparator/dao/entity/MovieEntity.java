package ie.brandtone.moviescomparator.dao.entity;

import static ie.brandtone.moviescomparator.dao.AbstractMovieFactory.FAVOURITE_KEY;
import static ie.brandtone.moviescomparator.dao.AbstractMovieFactory.ID_KEY;
import static ie.brandtone.moviescomparator.dao.AbstractMovieFactory.RATING_KEY;
import static ie.brandtone.moviescomparator.dao.AbstractMovieFactory.TITLE_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.TO_STRING_SIMPLE_JSON_MOVIE_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.N_A;
import static ie.brandtone.moviescomparator.utils.Commons.getMessageFromBundle;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity representation for the MOVIE DB table with columns annotations and main queries.
 * 
 * @author Marco Pala
 */
@Entity
@Table(name = "MOVIE")
@NamedQueries({
    @NamedQuery(name = "FIND_MOVIE_BY_ID", query = "from MovieEntity m where m.id = :id"),
    @NamedQuery(name = "FIND_ALL_MOVIES", query = "from MovieEntity m")
})
public class MovieEntity implements Serializable
{
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -2658642326332077686L;

    /**
     * The generated ID columm.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    /**
     * The movie ID column.
     */
    @Column(name = "IMDB_ID")
    private String imdbId;

    /**
     * The movie title column.
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * The movie rating column.
     */
    @Column(name = "RATING")
    private Float rating;

    /**
     * The movie favourite flag column.
     */
    @Column(name = "FAVOURITE")
    private Boolean favourite;

    /**
     * Default constructor.
     */
    public MovieEntity()
    {
        this.imdbId = N_A;
        this.title = N_A;
        this.rating = 0.0f;
        this.favourite = false;
    }

    /**
     * Constructor with the given columns.
     * 
     * @param id The movie ID column
     * @param title The movie title column
     * @param rating The movie rating column
     */
    public MovieEntity(String id, String title, float rating)
    {
        this.imdbId = id;
        this.title = title;
        this.rating = rating;
        this.favourite = false;
    }

    /**
     * Get the generated ID.
     *
     * @return The generated ID
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * Set the generated ID.
     *
     * @param id The generated ID to set
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * Get the movie ID.
     *
     * @return The movie ID
     */
    public String getImdbId()
    {
        return imdbId;
    }

    /**
     * Set the movie ID.
     *
     * @param imdbId The movie ID to set
     */
    public void setImdbId(String imdbId)
    {
        this.imdbId = imdbId;
    }

    /**
     * Get the movie title.
     *
     * @return The movie title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Set the movie title.
     *
     * @param title The movie title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Get the movie rating.
     *
     * @return The movie rating
     */
    public Float getRating()
    {
        return rating;
    }

    /**
     * Set the movie rating.
     *
     * @param rating The movie rating to set
     */
    public void setRating(Float rating)
    {
        this.rating = rating;
    }

    /**
     * Get the movie favourite flag.
     *
     * @return The movie favourite flag
     */
    public Boolean getFavourite()
    {
        return favourite;
    }

    /**
     * Set the movie favourite flag.
     *
     * @param favourite The movie favourite flag to set
     */
    public void setFavourite(Boolean favourite)
    {
        this.favourite = favourite;
    }

    /**
     * {@link String} representation of the {@link MovieEntity}.
     * 
     * @return The String representation of the MovieEntity
     */
    @Override
    public String toString()
    {
        return getMessageFromBundle(TO_STRING_SIMPLE_JSON_MOVIE_KEY, ID_KEY, this.getImdbId(), TITLE_KEY, this.getTitle(), RATING_KEY, String.valueOf(this.getRating()), FAVOURITE_KEY,
                String.valueOf(this.getFavourite()));
    }

}