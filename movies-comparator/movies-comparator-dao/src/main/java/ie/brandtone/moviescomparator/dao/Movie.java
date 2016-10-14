package ie.brandtone.moviescomparator.dao;

import static ie.brandtone.moviescomparator.utils.Commons.literalOrNa;
import static ie.brandtone.moviescomparator.utils.Constants.OMDB_ID_FIELD;
import static ie.brandtone.moviescomparator.utils.Constants.OMDB_TITLE_FIELD;
import static ie.brandtone.moviescomparator.utils.Constants.OMDB_RATING_FIELD;

import org.json.JSONException;
import org.json.JSONObject;

import ie.brandtone.moviescomparator.dao.exception.BadMovieFormatException;

/**
 * Describes a movie with its relevant properties.
 * 
 * @author Marco Pala
 */
public class Movie
{
	/**
	 * The movie ID.
	 */
	private String id;

	/**
	 * The movie title.
	 */
	private String title;
	
	/**
	 * The movie rating.
	 */
	private float rating;

	/**
	 * Public constructor with the movie's attributes.
	 * 
	 * @param id The movie ID
	 * @param title The movie title
	 * @param rating The movie rating
	 */
	public Movie(String id, String title, float rating)
	{
		this.id = literalOrNa(id);
		this.title = literalOrNa(title);
		this.rating = rating;
	}

	/**
	 * Public constructor to create a {@link Movie} object starting from its JSON object representation.
	 * <br>
	 * TODO Move this method to a MovieFactory class<br>
	 * TODO Remember to doublecheck the movie title (the OMDb API will return movies also when the title partially matches one)<br>
	 * 
	 * @param jsonMovie The JSON object representation of the movie to create
	 * 
	 * @throws BadMovieFormatException in case of a bad JSON input format or conversion issues. 
	 */
	public Movie(JSONObject jsonMovie) throws BadMovieFormatException
	{
		try
		{
			// Get relevant movie's properties
			this.id = jsonMovie.getString(OMDB_ID_FIELD);
			this.title = jsonMovie.getString(OMDB_TITLE_FIELD);
			this.rating = Float.parseFloat(jsonMovie.getString(OMDB_RATING_FIELD));
		}
		catch (JSONException | NumberFormatException e)
		{
			// In case of any issue on getting movie's properties
			throw new BadMovieFormatException(e);
		}
	}

	/**
	 * Get the movie ID.
	 *
	 * @return The ID field
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Set the movie ID.
	 *
	 * @param id The ID to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Get the movie title.
	 *
	 * @return The title field
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Set the movie title.
	 *
	 * @param title The title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Get the movie rating.
	 *
	 * @return The rating field
	 */
	public float getRating()
	{
		return rating;
	}

	/**
	 * Set the movie rating.
	 *
	 * @param rating The rating to set
	 */
	public void setRating(float rating)
	{
		this.rating = rating;
	}
}
