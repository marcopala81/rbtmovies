package ie.brandtone.moviescomparator.dao.entity;

import static ie.brandtone.moviescomparator.utils.Commons.CHANGED_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.FAVOURITE_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.ID_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.RATING_KEY;
import static ie.brandtone.moviescomparator.utils.Commons.TITLE_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_MARKED_AS_FAVOURITE_MSG_KEY;
import static ie.brandtone.moviescomparator.utils.BundleKeyConstants.MOVIE_SCORE_UPDATED_MSG_KEY;
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

import org.apache.log4j.Logger;

import ie.brandtone.moviescomparator.dao.Movie;

/**
 * Entity representation for the MOVIE DB table with columns annotations and main queries.
 * 
 * @author Marco Pala
 * 
 */
@Entity
@Table(name = "MOVIE")
@NamedQueries({
    @NamedQuery(name = "FIND_MOVIE_BY_ID", query = "from MovieEntity m where m.id = :id"),
    @NamedQuery(name = "FIND_MOVIE_BY_TITLE", query = "from MovieEntity m where m.title = :title"),
    @NamedQuery(name = "FIND_ALL_MOVIES", query = "from MovieEntity m")
})
public class MovieEntity implements Movie, Serializable
{    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -2658642326332077686L;

    /**
     * The Apache Log4j logger.
     */
    private static final Logger LOGGER = Logger.getLogger(MovieEntity.class);
    
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
     * The movie changed flag.
     */
    @Column(name = "CHANGED")
    private Boolean changed;
    
    /**
     * Default constructor.
     */
    public MovieEntity()
    {
        this.id = -1;
        this.imdbId = N_A;
        this.title = N_A;
        this.rating = 0.0f;
        this.favourite = false;
        this.changed = false;
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
        this.id = -1;
        this.imdbId = id;
        this.title = title;
        this.rating = rating;
        this.favourite = false;
        this.changed = false;
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
     * {@inheritDoc}
     */
    @Override
    public String getImdbId()
    {
        return imdbId;
    }

    /**
     * Set the movie IMDb ID.
     *
     * @param imdbId The movie IMDb ID to set
     */
    public void setImdbId(String imdbId)
    {
        this.imdbId = imdbId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
     * {@inheritDoc}
     */
    @Override
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
        LOGGER.debug(getMessageFromBundle(MOVIE_SCORE_UPDATED_MSG_KEY,
                this.getTitle(), String.valueOf(this.getRating()), String.valueOf(rating)));
        this.rating = rating;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getFavourite()
    {
        return favourite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFavourite(Boolean favourite)
    {
        this.favourite = favourite;
        LOGGER.debug(getMessageFromBundle(MOVIE_MARKED_AS_FAVOURITE_MSG_KEY, this.getTitle()));
    }

    /**
     * Get the changed flag (to detect local modifications).
     *
     * @return The changed flag
     */
    public Boolean getChanged()
    {
        return changed;
    }

    /**
     * Set the changed flag.
     *
     * @param changed The changed flag to set
     */
    public void setChanged(Boolean changed)
    {
        this.changed = changed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return getMessageFromBundle(TO_STRING_SIMPLE_JSON_MOVIE_KEY,
                                    ID_KEY, this.getImdbId(),
                                    TITLE_KEY, this.getTitle(),
                                    RATING_KEY, String.valueOf(this.getRating()),
                                    FAVOURITE_KEY, String.valueOf(this.getFavourite()),
                                    CHANGED_KEY, String.valueOf(this.getChanged()));
    }

}