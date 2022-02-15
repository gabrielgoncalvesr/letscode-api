package letscode.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import letscode.api.entity.RankingEntity;

@Repository
@Transactional
public class RankingRepository extends BaseRepository<RankingEntity> {

	public RankingRepository() {
		super(RankingEntity.class);
	}

	public RankingEntity save(RankingEntity ranking) {
		return persist(ranking.getRankingId(), ranking);
	}

	public RankingEntity getUserRanking(String userId) {
		var query = query("SELECT r FROM ranking r WHERE r.userId = :userId");
		query.setParameter("userId", userId);

		return getUnique(query);
	}

	public List<RankingEntity> getRankingList() {
		var query = query("SELECT r FROM ranking r ORDER BY r.score DESC").setMaxResults(20);

		return query.getResultList();
	}
}