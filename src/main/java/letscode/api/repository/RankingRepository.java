package letscode.api.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import letscode.api.entity.RankingEntity;

@Repository
@Transactional
public class RankingRepository extends BaseRepository<RankingEntity> {

	public RankingRepository(EntityManager em) {
		super(em);
	}

	public RankingEntity getById(String matchId) {
		return em.find(RankingEntity.class, matchId);
	}

	public RankingEntity save(RankingEntity ranking) {
		return persist(ranking.getRankingId(), ranking);
	}

	public TypedQuery<RankingEntity> query(String query) {
		return em.createQuery(query, RankingEntity.class);
	}

}