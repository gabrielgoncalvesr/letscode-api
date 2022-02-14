package letscode.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieReducedSearchResponse {
	
	@JsonProperty("Title")
	private String title;

	@JsonProperty("imdbID")
	private String imdbId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	
}