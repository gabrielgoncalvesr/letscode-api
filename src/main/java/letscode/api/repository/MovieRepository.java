package letscode.api.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import letscode.api.entity.MovieEntity;

@Repository
@Transactional
public class MovieRepository extends BaseRepository<MovieEntity> {

	public MovieRepository() {
		super(MovieEntity.class);
	}

	public MovieEntity save(MovieEntity match) {
		return persist(match.getMovieId(), match);
	}

	public List<MovieEntity> getMovieSet() {
		var query = query("SELECT m FROM movie m ORDER BY RAND()").setMaxResults(2);

		return query.getResultList();
	}
}