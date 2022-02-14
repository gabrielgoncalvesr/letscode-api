package letscode.api.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import letscode.api.entity.MatchEntity;

@Repository
@Transactional
public class MatchRepository extends BaseRepository<MatchEntity> {

	public MatchRepository(EntityManager em) {
		super(em);
	}

	public MatchEntity getById(String matchId) {
		return em.find(MatchEntity.class, matchId);
	}

	public MatchEntity save(MatchEntity match) {
		return persist(match.getMatchId(), match);
	}

	public TypedQuery<MatchEntity> query(String query) {
		return em.createQuery(query, MatchEntity.class);
	}

}