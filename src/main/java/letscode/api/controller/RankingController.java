package letscode.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import letscode.api.entity.RankingEntity;
import letscode.api.service.RankingService;

@RestController
@RequestMapping("/rankings")
public class RankingController {

	@Autowired
	private RankingService rankingService;

	@GetMapping
	public ResponseEntity<List<RankingEntity>> getMatchById() {
		List<RankingEntity> rankingList = rankingService.getRanking();

		return ResponseEntity.ok(rankingList);
	}
}