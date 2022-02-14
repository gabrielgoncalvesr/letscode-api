package letscode.api.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import letscode.api.entity.MovieEntity;

@Repository
@Transactional
public class MovieRepository extends BaseRepository<MovieEntity> {

	public MovieRepository(EntityManager em) {
		super(em);
	}

	public MovieEntity getById(String movieId) {
		return em.find(MovieEntity.class, movieId);
	}

	public MovieEntity save(MovieEntity match) {
		return persist(match.getMovieId(), match);
	}

	public TypedQuery<MovieEntity> query(String query) {
		return em.createQuery(query, MovieEntity.class);
	}

}