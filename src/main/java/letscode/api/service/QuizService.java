package letscode.api.service;

import java.util.Date;
import java.util.List;

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
import letscode.api.repository.MatchRepository;
import letscode.api.repository.MovieRepository;
import letscode.api.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	private IMDBExternal apiExternal;

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private MovieRepository movieRepository;

	public QuizEntity getQuizById(String quizId) {
		QuizEntity quiz = quizRepository.getQuizById(quizId);
		if (quiz == null) {
			throw new ResponseException(HttpStatus.NOT_FOUND, "quiz.not_found");
		}

		return quiz;
	}

	public QuizEntity getActiveQuiz() {
		getActiveMatch();

		QuizEntity quiz = quizRepository.getActiveQuiz();
		if (quiz == null) {
			throw new ResponseException(HttpStatus.BAD_REQUEST, "quiz.quiz_not_started");
		}

		return quiz;
	}

	public MatchEntity getActiveMatch() {
		MatchEntity match = matchRepository.getActiveMatch();
		if (match == null) {
			throw new ResponseException(HttpStatus.BAD_REQUEST, "match.none_started");
		}

		return match;
	}

	public boolean validateQuiz(String option) {
		QuizEntity quiz = getActiveQuiz();

		boolean correctAnswer = quiz.getCorrectOption().equals(option);

		quiz.setAnswered(true);
		quiz.setCorrect(correctAnswer);
		quiz.setUpdateDate(new Date());

		quizRepository.save(quiz);

		return correctAnswer;
	}

	public QuizModelResponse nextQuiz() {
		MatchEntity match = getActiveMatch();

		QuizEntity quiz = quizRepository.getActiveQuiz();
		if (quiz != null) {
			throw new ResponseException(HttpStatus.BAD_REQUEST, "quiz.previous_unanswered");
		}

		List<MovieEntity> movieSet = movieRepository.getMovieSet();

		QuizEntity quizSet = quizRepository.searchMovieSet(movieSet.get(0).getMovieId(), movieSet.get(1).getMovieId());

		if (quizSet != null) {
			for (int i = 0; i < 5; i++) {
				movieSet = movieRepository.getMovieSet();

				quizSet = quizRepository.searchMovieSet(movieSet.get(0).getMovieId(), movieSet.get(1).getMovieId());
				if (quizSet == null) {
					break;
				}
			}
		}

		MovieModelResponse firstMovie = apiExternal.getMovieById(movieSet.get(0).getImdbId());
		MovieModelResponse secondMovie = apiExternal.getMovieById(movieSet.get(1).getImdbId());

		double firstMovieRating = Double.parseDouble(firstMovie.getImdbRating())
				* Double.valueOf(firstMovie.getImdbVotes().replace(",", ""));
		double secondMovieRating = Double.parseDouble(secondMovie.getImdbRating())
				* Double.valueOf(secondMovie.getImdbVotes().replace(",", ""));

		String correctOption = firstMovieRating > secondMovieRating ? movieSet.get(0).getMovieId()
				: movieSet.get(1).getMovieId();

		QuizEntity newQuiz = new QuizEntity(movieSet.get(0).getMovieId(), movieSet.get(1).getMovieId(),
				AuthHelper.getUserLogged(), match.getMatchId(), correctOption);

		newQuiz = quizRepository.save(newQuiz);

		var firstMovieModel = QuizMovieModelResponse.parse(firstMovie);
		firstMovieModel.setMovieId(movieSet.get(0).getMovieId());

		var secondMovieModel = QuizMovieModelResponse.parse(secondMovie);
		secondMovieModel.setMovieId(movieSet.get(1).getMovieId());

		return new QuizModelResponse(newQuiz.getQuizId(), firstMovieModel, secondMovieModel);
	}

	public void endActiveQuiz() {
		var quiz = quizRepository.getActiveQuiz();

		quiz.setAnswered(true);
		quiz.setUpdateDate(new Date());

		quizRepository.save(quiz);
	}
}