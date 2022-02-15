package letscode.api.model.response;

public class QuizModelResponse {

	private String quizId;

	private QuizMovieModelResponse firstMovie;

	private QuizMovieModelResponse secondMovie;

	public QuizModelResponse(String quizId, QuizMovieModelResponse firstMovie, QuizMovieModelResponse secondMovie) {
		this.quizId = quizId;
		this.firstMovie = firstMovie;
		this.secondMovie = secondMovie;
	}

	public String getQuizId() {
		return quizId;
	}

	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}

	public QuizMovieModelResponse getFirstMovie() {
		return firstMovie;
	}

	public void setFirstMovie(QuizMovieModelResponse firstMovie) {
		this.firstMovie = firstMovie;
	}

	public QuizMovieModelResponse getSecondMovie() {
		return secondMovie;
	}

	public void setSecondMovie(QuizMovieModelResponse secondMovie) {
		this.secondMovie = secondMovie;
	}
}