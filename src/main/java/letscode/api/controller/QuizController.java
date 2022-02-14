package letscode.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import letscode.api.exception.ResponseException;
import letscode.api.model.request.ValidateQuizModelRequest;
import letscode.api.model.response.QuizModelResponse;
import letscode.api.service.QuizService;

@RestController
@RequestMapping("/quizes")
public class QuizController {

	@Autowired
	private QuizService quizService;

	@PostMapping
	public ResponseEntity<QuizModelResponse> nextQuiz() {
		QuizModelResponse match = quizService.nextQuiz();

		return ResponseEntity.status(HttpStatus.CREATED).body(match);
	}

	@PutMapping("/{quizId}")
	public ResponseEntity<?> validateQuiz(@PathVariable("quizId") String quizId,
			@RequestBody ValidateQuizModelRequest model) {
		boolean validated = quizService.validateQuiz(quizId, model.getOption());

		if (!validated) {
			throw new ResponseException(HttpStatus.BAD_REQUEST, "quiz.incorrect_answer");
		}

		return ResponseEntity.ok().build();
	}
}