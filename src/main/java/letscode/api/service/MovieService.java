package letscode.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import letscode.api.entity.MovieEntity;
import letscode.api.external.IMDBExternal;
import letscode.api.model.response.MovieReducedSearchResponse;
import letscode.api.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private IMDBExternal apiExternal;

	@PostConstruct
	private Object feedMovies() {
		List<MovieReducedSearchResponse> moviesList = new ArrayList<>();

		for (int page = 1; page <= 20; page++) {
			String searchTerm = page % 2 == 0 ? "star" : "life";

			var response = apiExternal.searchMovies(searchTerm, String.valueOf(page));

			moviesList.addAll(response.getMovies());
		}

		for (var movie : moviesList) {
			movieRepository.save(new MovieEntity(movie.getImdbId(), movie.getTitle()));
		}

		return moviesList;
	}

	public MovieEntity getRandomMovie() {
		TypedQuery<MovieEntity> query = movieRepository.query("SELECT m FROM movie m ORDER BY RAND()");

		return query.getResultList().stream().findFirst().orElse(null);
	}

}