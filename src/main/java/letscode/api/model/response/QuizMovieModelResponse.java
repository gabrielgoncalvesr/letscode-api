package letscode.api.model.response;

public class QuizMovieModelResponse {

	private String movieId;

	private String title;

	private String year;

	private String released;

	private String runtime;

	private String genre;

	private String director;

	private String writer;

	private String actors;

	private String plot;

	private String language;

	private String country;

	private String poster;

	// @JsonProperty(value = "imdbRating", access = Access.WRITE_ONLY)
	private String imdbRating;

	// @JsonProperty(value = "imdbVotes", access = Access.WRITE_ONLY)
	private String imdbVotes;

	private String imdbId;

	private String boxOffice;

	private String DVD;

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

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

	public void setDVD(String DVD) {
		this.DVD = DVD;
	}

	public static QuizMovieModelResponse parse(MovieModelResponse model) {
		QuizMovieModelResponse quizModel = new QuizMovieModelResponse();

		quizModel.setTitle(model.getTitle());
		quizModel.setYear(model.getYear());
		quizModel.setReleased(model.getReleased());
		quizModel.setRuntime(model.getRuntime());
		quizModel.setGenre(model.getGenre());
		quizModel.setDirector(model.getDirector());
		quizModel.setWriter(model.getWriter());
		quizModel.setActors(model.getActors());
		quizModel.setPlot(model.getPlot());
		quizModel.setLanguage(model.getLanguage());
		quizModel.setCountry(model.getCountry());
		quizModel.setPoster(model.getPoster());
		quizModel.setImdbRating(model.getImdbRating());
		quizModel.setImdbVotes(model.getImdbVotes());
		quizModel.setImdbId(model.getImdbId());
		quizModel.setBoxOffice(model.getBoxOffice());
		quizModel.setDVD(model.getDVD());

		return quizModel;
	}
}