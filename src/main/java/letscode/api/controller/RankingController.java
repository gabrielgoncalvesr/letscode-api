package letscode.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import letscode.api.entity.RankingEntity;
import letscode.api.repository.RankingRepository;

@RestController
@RequestMapping("/rankings")
public class RankingController {

	@Autowired
	private RankingRepository rankingRepository;

	@GetMapping
	public ResponseEntity<List<RankingEntity>> getRankingList() {
		var rankingList = rankingRepository.getRankingList();

		return ResponseEntity.ok(rankingList);
	}
}