package letscode.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import letscode.api.entity.MatchEntity;
import letscode.api.service.MatchService;
import letscode.api.service.MovieService;

@RestController
@RequestMapping("/matches")
public class MatchController {

	@Autowired
	private MatchService matchService;

	@Autowired
	private MovieService movieService;

	@GetMapping("/{matchId}")
	public ResponseEntity<MatchEntity> getMatchById(@PathVariable("matchId") String matchId) {
		MatchEntity match = matchService.getMatchById(matchId);

		return ResponseEntity.ok(match);
	}

	@GetMapping("/active")
	public ResponseEntity<MatchEntity> getActiveMatch() {
		MatchEntity match = matchService.getActiveMatch();

		return ResponseEntity.ok(match);
	}

	@PostMapping
	public ResponseEntity<MatchEntity> startMatch() {
		MatchEntity match = matchService.startMatch();

		return ResponseEntity.status(HttpStatus.CREATED).body(match);
	}

	@PutMapping
	public ResponseEntity<MatchEntity> endMatch() {
		MatchEntity match = matchService.endMatch();

		return ResponseEntity.ok(match);
	}

//	@GetMapping("/movie")
//	public ResponseEntity<?> movie() {
//		var match = movieService.feedMovies();
//
//		return ResponseEntity.ok(match);
//	}
}