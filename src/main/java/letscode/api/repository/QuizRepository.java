package letscode.api.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import letscode.api.entity.QuizEntity;

@Repository
@Transactional
public class QuizRepository extends BaseRepository<QuizEntity> {

	public QuizRepository(EntityManager em) {
		super(em);
	}

	public QuizEntity getById(String matchId) {
		return em.find(QuizEntity.class, matchId);
	}

	public QuizEntity save(QuizEntity match) {
		return persist(match.getMatchId(), match);
	}

	public TypedQuery<QuizEntity> query(String query) {
		return em.createQuery(query, QuizEntity.class);
	}

}