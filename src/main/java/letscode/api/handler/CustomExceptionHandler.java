package letscode.api.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import letscode.api.exception.ResponseException;
import letscode.api.model.ErrorModel;
import letscode.api.model.ValidationErrorModel;
import letscode.api.model.ValidationModel;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(ResponseException.class)
	public ResponseEntity<ErrorModel> handleException(ResponseException e) {
		return ResponseEntity.status(e.getHttpStatus()).body(e.getErrorModel());
	}
	
	@ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ValidationModel> handleException(BindException exception) {
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();
        
        List<ValidationErrorModel> errorList = errors.stream().map(x -> new ValidationErrorModel(x.getField(), x.getDefaultMessage())).toList();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationModel(errorList));
    }
}