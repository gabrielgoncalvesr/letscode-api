package letscode.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import letscode.api.repository.MatchRepository;
import letscode.api.service.MatchService;
import letscode.api.service.RankingService;

@RunWith(SpringRunner.class)
@WebMvcTest(MatchController.class)
@ContextConfiguration(classes = { MatchService.class, RankingService.class, MatchRepository.class })
public class MatchControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void getMatchById() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/users");
		MvcResult result = mvc.perform(request).andReturn();

		assertEquals("teste", result.getResponse().getContentAsString());
	}
}
