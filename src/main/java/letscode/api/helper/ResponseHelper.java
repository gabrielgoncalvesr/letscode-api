package letscode.api.helper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseHelper {
	public static String parse(Object object) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			return null;
		}
	}
}