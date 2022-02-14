package letscode.api.service;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import letscode.api.entity.RankingEntity;
import letscode.api.helper.AuthHelper;
import letscode.api.repository.RankingRepository;

@Service
public class RankingService {

	@Autowired
	private RankingRepository rankingRepository;

	public RankingEntity getUserRanking(String userId) {
		TypedQuery<RankingEntity> query = rankingRepository.query("SELECT r FROM ranking r WHERE r.userId = :userId");
		query.setParameter("userId", userId);

		return query.getResultList().stream().findFirst().orElse(null);
	}

	public RankingEntity updateUserRanking(int totalHits, int totalQuizzes) {
		double score = (totalHits * 100) / totalQuizzes;
		
		RankingEntity ranking = getUserRanking(AuthHelper.getUserLogged());
		if (ranking == null) {
			ranking = new RankingEntity(AuthHelper.getUserLogged());
		} else {
			if(score > ranking.getScore()) {
				ranking.setScore(score);
				ranking.setUpdateDate(new Date());
			}
		}

		rankingRepository.save(ranking);

		return ranking;
	}

	public List<RankingEntity> getRanking() {
		TypedQuery<RankingEntity> query = rankingRepository.query("SELECT r FROM ranking r ORDER BY r.score DESC").setMaxResults(20);
		
		return query.getResultList();
	}

}