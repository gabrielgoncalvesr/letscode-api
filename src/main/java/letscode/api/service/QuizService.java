package letscode.api.service;

import java.util.Date;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import letscode.api.entity.MatchEntity;
import letscode.api.entity.MovieEntity;
import letscode.api.entity.QuizEntity;
import letscode.api.exception.ResponseException;
import letscode.api.external.IMDBExternal;
import letscode.api.helper.AuthHelper;
import letscode.api.model.response.MovieModelResponse;
import letscode.api.model.response.QuizModelResponse;
import letscode.api.model.response.QuizMovieModelResponse;
import letscode.api.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private MovieService movieService;

	@Autowired
	private MatchService matchService;

	@Autowired
	private IMDBExternal apiExternal;

	public QuizEntity getQuizById(String quizId) {
		TypedQuery<QuizEntity> query = quizRepository
				.query("SELECT q FROM quiz q WHERE q.quizId = :quizId AND q.userId = :userId");
		query.setParameter("quizId", quizId);
		query.setParameter("userId", AuthHelper.getUserLogged());

		QuizEntity quiz = query.getResultList().stream().findFirst().orElse(null);

		if (quiz == null) {
			throw new ResponseException(HttpStatus.BAD_REQUEST, "quiz.not_found");
		}

		return quiz;
	}

	public boolean validateQuiz(String quizId, String option) {
		MatchEntity match = matchService.getActiveMatch();

		if (match.getErrorAttempts() > 3) {
			throw new ResponseException(HttpStatus.BAD_REQUEST, "quiz.error_attempts_exceeded");
		}

		QuizEntity quiz = getQuizById(quizId);

		if (quiz.getAnswered()) {
			throw new ResponseException(HttpStatus.BAD_REQUEST, "quiz.already_answered");
		}

		boolean correctAnswer = quiz.getCorrectOption().equals(option);

		quiz.setCorrect(correctAnswer);
		quiz.setAnswered(true);
		quiz.setUpdateDate(new Date());

		quizRepository.save(quiz);

		if (correctAnswer) {
			match.setScore(match.getScore() + 1);
		} else {
			match.setErrorAttempts(match.getScore() + 1);

			if (match.getScore() == 3) {
				matchService.endMatch();

				throw new ResponseException(HttpStatus.BAD_REQUEST, "quiz.game_ended_by_error_attempt");
			}
		}

		matchService.updateMatch(match);

		return correctAnswer;
	}

	public QuizModelResponse nextQuiz() {
		MatchEntity match = matchService.getActiveMatch();

		TypedQuery<QuizEntity> query = quizRepository
				.query("SELECT q FROM quiz q WHERE q.answered IS NULL AND q.userId = :userId");
		query.setParameter("userId", AuthHelper.getUserLogged());

		QuizEntity quiz = query.getResultList().stream().findFirst().orElse(null);

		if (quiz != null) {
			throw new ResponseException(HttpStatus.BAD_REQUEST, "quiz.previous_unanswered");
		}

		MovieEntity firstRandomMovie = movieService.getRandomMovie();
		MovieEntity secondRandomMovie = movieService.getRandomMovie();

		MovieModelResponse firstMovie = apiExternal.getMovieById(firstRandomMovie.getImdbId());
		MovieModelResponse secondMovie = apiExternal.getMovieById(secondRandomMovie.getImdbId());

		double firstMovieRating = Double.parseDouble(firstMovie.getImdbRating())
				* Double.valueOf(firstMovie.getImdbVotes().replace(",", ""));
		double secondMovieRating = Double.parseDouble(secondMovie.getImdbRating())
				* Double.valueOf(secondMovie.getImdbVotes().replace(",", ""));

		String correctOption = firstMovieRating > secondMovieRating ? firstRandomMovie.getMovieId()
				: secondRandomMovie.getMovieId();

		QuizEntity newQuiz = new QuizEntity(firstRandomMovie.getMovieId(), secondRandomMovie.getMovieId(),
				AuthHelper.getUserLogged(), match.getMatchId(), correctOption);

		newQuiz = quizRepository.save(newQuiz);

		var firstMovieModel = QuizMovieModelResponse.parse(firstMovie);
		firstMovieModel.setMovieId(firstRandomMovie.getMovieId());

		var secondMovieModel = QuizMovieModelResponse.parse(secondMovie);
		secondMovieModel.setMovieId(secondRandomMovie.getMovieId());

		return new QuizModelResponse(newQuiz.getQuizId(), firstMovieModel, secondMovieModel);
	}
}