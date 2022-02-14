package letscode.api.exception;

import org.springframework.http.HttpStatus;

import letscode.api.model.ErrorModel;

public class ResponseException extends RuntimeException {

	private static final long serialVersionUID = 4462211623116187392L;

	private ErrorModel errorModel;

	private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

	public ResponseException(HttpStatus httpStatus, String param) {
		super(param);

		this.httpStatus = httpStatus;
		this.errorModel = new ErrorModel(param);
	}

	public ErrorModel getErrorModel() {
		return errorModel;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}