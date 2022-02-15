package letscode.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class MovieModelResponse {

	@JsonProperty("movieId")
	private String movieId;

	@JsonProperty("Title")
	private String title;

	@JsonProperty("Year")
	private String year;

	@JsonProperty("Released")
	private String released;

	@JsonProperty("Runtime")
	private String runtime;

	@JsonProperty("Genre")
	private String genre;

	@JsonProperty("Director")
	private String director;

	@JsonProperty("Writer")
	private String writer;

	@JsonProperty("Actors")
	private String actors;

	@JsonProperty("Plot")
	private String plot;

	@JsonProperty("Language")
	private String language;

	@JsonProperty("Country")
	private String country;

	@JsonProperty("Poster")
	private String poster;

	@JsonProperty(value = "imdbRating", access = Access.WRITE_ONLY)
	private String imdbRating;

	@JsonProperty(value = "imdbVotes", access = Access.WRITE_ONLY)
	private String imdbVotes;

	@JsonProperty("imdbID")
	private String imdbId;

	@JsonProperty("BoxOffice")
	private String boxOffice;

	@JsonProperty("DVD")
	private String DVD;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getImdbVotes() {
		return imdbVotes;
	}

	public void setImdbVotes(String imdbVotes) {
		this.imdbVotes = imdbVotes;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getBoxOffice() {
		return boxOffice;
	}

	public void setBoxOffice(String boxOffice) {
		this.boxOffice = boxOffice;
	}

	public String getDVD() {
		return DVD;
	}

	public void setDVD(String dVD) {
		DVD = dVD;
	}
}