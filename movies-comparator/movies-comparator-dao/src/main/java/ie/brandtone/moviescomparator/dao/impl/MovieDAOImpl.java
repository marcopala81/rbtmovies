package ie.brandtone.moviescomparator.dao.impl;

import java.util.ArrayList;
import java.util.List;

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
        Integer movieId = (Integer) this.sessionFactory.getCurrentSession().save(movie);
        
        return movieId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateMovie(MovieEntity movie)
    {
        this.sessionFactory.getCurrentSession().update(movie);
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
            this.sessionFactory.getCurrentSession().delete(movie);
        }
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieEntity getMovieById(Integer movieId)
    {
        MovieEntity movie = null;
        List<?> queryResult = sessionFactory.getCurrentSession().getNamedQuery("FIND_MOVIE_BY_ID").setInteger("id", movieId).list();
        
        if (queryResult != null && !queryResult.isEmpty())
        {
            movie = (MovieEntity) queryResult.get(0);
        }
        
        return movie;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MovieEntity> getAllMovies()
    {
        List<?> queryResult = sessionFactory.getCurrentSession().getNamedQuery("FIND_ALL_MOVIES").list(); 
        
        // Cast correct type
        List<MovieEntity> movieList = new ArrayList<MovieEntity>();
        for (Object movie : queryResult)
        {
            movieList.add((MovieEntity) movie);
        }
        
        return (List<MovieEntity>) movieList;
    }   
}
