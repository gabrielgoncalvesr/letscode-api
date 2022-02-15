package letscode.api.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieSearchResponse {

	@JsonProperty("totalResults")
	private String totalResults;

	@JsonProperty("Search")
	private List<MovieReducedSearchResponse> movies;

	public String getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}

	public List<MovieReducedSearchResponse> getMovies() {
		return movies;
	}

	public void setMovies(List<MovieReducedSearchResponse> movies) {
		this.movies = movies;
	}
}