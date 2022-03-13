package letscode.api.model;

import java.util.List;

public class ValidationModel {
	
	private List<ValidationErrorModel> errors;

	public ValidationModel(List<ValidationErrorModel> errors) {
		this.errors = errors;
	}

	public List<ValidationErrorModel> getErrors() {
		return errors;
	}

	public void setErrors(List<ValidationErrorModel> errors) {
		this.errors = errors;
	}
	
}