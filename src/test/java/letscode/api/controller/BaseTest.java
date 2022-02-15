package letscode.api.controller;

import java.nio.charset.Charset;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class BaseTest {
	
	private HttpHeaders headers;

	@Autowired
	private MockMvc mvc;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	protected MockHttpServletResponse GET(String path) throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(path)).andReturn();

		return result.getResponse();
	}
	
	protected MockHttpServletResponse POST(String path, JSONObject body, HttpHeaders headers) throws Exception {
		MvcResult result = mvc.perform(
				MockMvcRequestBuilders
				.post(path)
				.contentType(MediaType.APPLICATION_JSON)
				.headers(headers).content(body.toString()))
				.andReturn();

		return result.getResponse();
	}

	protected MockHttpServletResponse POST(String path, JSONObject body) throws Exception {
		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.post(path).contentType(MediaType.APPLICATION_JSON).content(body.toString()))
				.andReturn();

		return result.getResponse();
	}
	
	protected void setHeader(String key, String value) {
		headers = headers == null ? new HttpHeaders() : headers;
		headers.add(key, value);
	}

	protected JSONObject createObject() {
		return new JSONObject();
	}
}