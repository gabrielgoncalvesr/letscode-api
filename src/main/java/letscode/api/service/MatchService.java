package letscode.api.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import letscode.api.entity.MatchEntity;
import letscode.api.enumeration.MatchStatusEnum;
import letscode.api.exception.ResponseException;
import letscode.api.helper.AuthHelper;
import letscode.api.repository.MatchRepository;

@Service
public class MatchService {

	@Autowired
	private MatchRepository matchRepository;

	public MatchEntity getMatchById(String matchId) {
		MatchEntity match = matchRepository.getUserMatchById(matchId);
		if (match == null) {
			throw new ResponseException(HttpStatus.NOT_FOUND, "match.not_found");
		}

		return match;
	}

	public MatchEntity getActiveMatch() {
		MatchEntity match = matchRepository.getActiveMatch();
		if (match == null) {
			throw new ResponseException(HttpStatus.BAD_REQUEST, "match.none_started");
		}

		return match;
	}

	public MatchEntity startMatch() {
		MatchEntity match = matchRepository.getActiveMatch();
		if (match != null) {
			throw new ResponseException(HttpStatus.BAD_REQUEST, "match.already_started");
		}

		MatchEntity newMatch = new MatchEntity(AuthHelper.getUserLogged());
		matchRepository.save(newMatch);

		return newMatch;
	}

	public MatchEntity endMatch() {
		MatchEntity match = getActiveMatch();

		match.setEndDate(new Date());
		match.setUpdateDate(new Date());
		match.setStatus(MatchStatusEnum.ENDED);

		matchRepository.save(match);

		return match;
	}

	public MatchEntity updateMatchScore(boolean correctAnswer) {
		MatchEntity match = getActiveMatch();

		if (correctAnswer) {
			match.setScore(match.getScore() + 1);
		} else {
			match.setErrorAttempts(match.getScore() + 1);
		}

		match.setUpdateDate(new Date());
		matchRepository.save(match);

		if (match.getErrorAttempts() == 3) {
			endMatch();

			throw new ResponseException(HttpStatus.BAD_REQUEST, "quiz.game_ended_by_error_attempt");
		}

		return match;
	}
}