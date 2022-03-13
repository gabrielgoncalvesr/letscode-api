package letscode.api.model;

public class ValidationErrorModel {

	private String param;
	private String message;

	public ValidationErrorModel(String param, String message) {
		this.param = param;
		this.message = message;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}