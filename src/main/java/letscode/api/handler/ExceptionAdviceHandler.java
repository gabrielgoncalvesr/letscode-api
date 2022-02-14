package letscode.api.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import letscode.api.exception.ResponseException;
import letscode.api.model.ErrorModel;

@ControllerAdvice
public class ExceptionAdviceHandler {

	@ExceptionHandler(ResponseException.class)
	public ResponseEntity<ErrorModel> handleException(ResponseException e) {
		return ResponseEntity.status(e.getHttpStatus()).body(e.getErrorModel());
	}
}