package ie.brandtone.moviescomparator.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NamedQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ie.brandtone.moviescomparator.dao.MovieDAO;
import ie.brandtone.moviescomparator.dao.entity.MovieEntity;

/**
 * Implementation of the {@link MovieDAO} interface.
 * 
 * @author Marco Pala
 */
@Repository
@Transactional
public class MovieDAOImpl implements MovieDAO
{    
    /**
     * The session factory.
     */
    @Autowired
    private SessionFactory sessionFactory;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer insertMovie(MovieEntity movie)
    {
        Integer id = -1;
        // Check if movie already in
        MovieEntity duplicated = getMovieByTitle(movie.getTitle());
        
        if (duplicated == null)
        {
            // No duplicate
            id = (Integer) getSession().save(movie);
        }
            
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateMovie(MovieEntity movie)
    {
        getSession().update(movie);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMovie(Integer movieId)
    {
        MovieEntity movie = (MovieEntity) sessionFactory.getCurrentSession().load(MovieEntity.class, movieId);
        if (movie != null)
        {
            getSession().delete(movie);
        }
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieEntity getMovieById(Integer movieId)
    {
        List<?> queryResult = getNamedQuery("FIND_MOVIE_BY_ID").setInteger("id", movieId).list();
        return getUniqueResult(queryResult);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public MovieEntity getMovieByTitle(String movieTitle)
    {
        List<?> queryResult = getNamedQuery("FIND_MOVIE_BY_TITLE").setString("title", movieTitle).list();
        return getUniqueResult(queryResult);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MovieEntity> getAllMovies()
    {
        List<?> queryResult = getNamedQuery("FIND_ALL_MOVIES").list(); 
        
        // Cast to the specific type (suppress Java Generics warning)
        List<MovieEntity> movieList = new ArrayList<MovieEntity>();
        for (Object movie : queryResult)
        {
            movieList.add((MovieEntity) movie);
        }
        
        return (List<MovieEntity>) movieList;
    }

    /**
     * Get the current {@link Session} from the {@link SessionFactory}} 
     * 
     * @return The current session
     */
    private Session getSession()
    {
        return this.sessionFactory.getCurrentSession();
    }
    
    /**
     * Get the {@link NamedQuery} for the given name.
     * 
     * @param queryName The name for the query
     * 
     * @return The named query from the {@link MovieEntity} annotated set
     */
    private Query getNamedQuery(String queryName)
    {
        return getSession().getNamedQuery(queryName);
    }
    
    /**
     * Check if the given query-result {@link List} has a unique record and return it (first element).
     * 
     * @param queryResult The query-result to check
     * 
     * @return The unique record (if any)
     */
    private MovieEntity getUniqueResult(List<?> queryResult)
    {
        MovieEntity movie = null;
        if (queryResult != null && queryResult.size() == 1)
        {
            movie = (MovieEntity) queryResult.get(0);
        }
        return movie;
    }    
}
