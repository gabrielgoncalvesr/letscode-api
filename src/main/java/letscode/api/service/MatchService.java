package letscode.api.service;

import java.util.Date;

import javax.persistence.TypedQuery;

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

	@Autowired
	private RankingService rankingService;

	public MatchEntity getMatchById(String matchId) {
		TypedQuery<MatchEntity> query = matchRepository.query("SELECT m FROM match m WHERE m.matchId = :matchId");
		query.setParameter("matchId", matchId);
		
		MatchEntity match = query.getResultList().stream().findFirst().orElse(null);

		if (match == null) {
			throw new ResponseException(HttpStatus.NOT_FOUND, "match.not_found");
		}
		
		return match;
	}

	public MatchEntity startMatch() {
		TypedQuery<MatchEntity> query = matchRepository
				.query("SELECT m FROM match m WHERE m.status = :status AND m.userId = :userId");
		query.setParameter("status", MatchStatusEnum.STARTED);
		query.setParameter("userId", AuthHelper.getUserLogged());

		MatchEntity match = query.getResultList().stream().findFirst().orElse(null);

		if (match != null) {
			throw new ResponseException(HttpStatus.BAD_REQUEST, "match.already_started");
		}

		MatchEntity newMatch = new MatchEntity(AuthHelper.getUserLogged());

		matchRepository.save(newMatch);

		return newMatch;
	}

	public MatchEntity endMatch() {
		TypedQuery<MatchEntity> query = matchRepository
				.query("SELECT m FROM match m WHERE m.status = :status AND m.userId = :userId");
		query.setParameter("status", MatchStatusEnum.STARTED);
		query.setParameter("userId", AuthHelper.getUserLogged());

		MatchEntity match = query.getResultList().stream().findFirst().orElse(null);

		if (match == null) {
			throw new ResponseException(HttpStatus.BAD_REQUEST, "match.none_started");
		}

		match.setEndDate(new Date());
		match.setUpdateDate(new Date());
		match.setStatus(MatchStatusEnum.ENDED);
		
		matchRepository.save(match);

		// rankingService.updateUserRanking();

		return match;
	}

	public MatchEntity getActiveMatch() {
		TypedQuery<MatchEntity> query = matchRepository
				.query("SELECT m FROM match m WHERE m.status = :status AND m.userId = :userId");
		query.setParameter("status", MatchStatusEnum.STARTED);
		query.setParameter("userId", AuthHelper.getUserLogged());

		MatchEntity match = query.getResultList().stream().findFirst().orElse(null);

		if (match == null) {
			throw new ResponseException(HttpStatus.BAD_REQUEST, "match.none_started");
		}

		return match;
	}
	
	public MatchEntity updateMatch(MatchEntity match) {
		match.setUpdateDate(new Date());
		matchRepository.save(match);

		return match;
	}
}