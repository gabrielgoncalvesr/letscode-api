package letscode.api.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ValidateQuizModelRequest {

	@NotNull
	@Size(min = 32, max = 32)
	private String option;

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
}