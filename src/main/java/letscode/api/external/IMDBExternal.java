package letscode.api.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import letscode.api.model.response.MovieModelResponse;
import letscode.api.model.response.MovieSearchResponse;

@Component
public class IMDBExternal extends BaseExternal {

	@Autowired
	private Environment env;

	public MovieSearchResponse searchMovies(String searchWord, String page) {
		String apiKey = env.getProperty("imdb_api.api_key");
		String apiHost = env.getProperty("imdb_api.api_host");

		String queryString = String.format("?apikey=%s&plot=%s&type=%s&s=%s&page=%s", apiKey, "full", "movie",
				searchWord, page);

		return (MovieSearchResponse) GET(apiHost + queryString, MovieSearchResponse.class);
	}

	public MovieModelResponse getMovieById(String imdbId) {
		String apiKey = env.getProperty("imdb_api.api_key");
		String apiHost = env.getProperty("imdb_api.api_host");

		String queryString = String.format("?apikey=%s&plot=%s&i=%s", apiKey, "full", imdbId);

		return (MovieModelResponse) GET(apiHost + queryString, MovieModelResponse.class);
	}
}