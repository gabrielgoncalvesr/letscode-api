package letscode.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import letscode.api.entity.QuizEntity;
import letscode.api.exception.ResponseException;
import letscode.api.model.request.ValidateQuizModelRequest;
import letscode.api.model.response.QuizModelResponse;
import letscode.api.service.MatchService;
import letscode.api.service.QuizService;
import letscode.api.service.RankingService;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

	@Autowired
	private QuizService quizService;

	@Autowired
	private MatchService matchService;
	
	@Autowired
	private RankingService rankingService;

	@GetMapping
	public ResponseEntity<QuizEntity> getActiveQuiz() {
		var quiz = quizService.getActiveQuiz();

		return ResponseEntity.ok(quiz);
	}

	@PostMapping
	public ResponseEntity<QuizModelResponse> nextQuiz() {
		var quiz = quizService.nextQuiz();

		return ResponseEntity.status(HttpStatus.CREATED).body(quiz);
	}

	@PutMapping
	public ResponseEntity<?> validateQuiz(@RequestBody ValidateQuizModelRequest model) {
		boolean correctAnswer = quizService.validateQuiz(model.getOption());

		var match = matchService.updateMatchScore(correctAnswer);
		
		if (match.getErrorAttempts() == 3) {
			matchService.endMatch();
			rankingService.updateUserRanking(match.getMatchId());

			throw new ResponseException(HttpStatus.BAD_REQUEST, "quiz.game_ended_by_error_attempt");
		}

		if (!correctAnswer) {
			throw new ResponseException(HttpStatus.BAD_REQUEST, "quiz.incorrect_answer");
		}

		return ResponseEntity.ok().build();
	}
}