package letscode.api.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import letscode.api.entity.MatchEntity;
import letscode.api.enumeration.MatchStatusEnum;
import letscode.api.helper.AuthHelper;

@Repository
@Transactional
public class MatchRepository extends BaseRepository<MatchEntity> {

	public MatchRepository() {
		super(MatchEntity.class);
	}

	public MatchEntity save(MatchEntity match) {
		return persist(match.getMatchId(), match);
	}

	public MatchEntity getActiveMatch() {
		var query = query("SELECT m FROM match m WHERE m.status = :status AND m.userId = :userId");
		query.setParameter("status", MatchStatusEnum.STARTED);
		query.setParameter("userId", AuthHelper.getUserLogged());

		return getUnique(query);
	}

	public MatchEntity getUserMatchById(String matchId) {
		var query = query("SELECT m FROM match m WHERE m.matchId = :matchId AND m.userId = :userId");
		query.setParameter("matchId", matchId);
		query.setParameter("userId", AuthHelper.getUserLogged());

		return getUnique(query);
	}
}