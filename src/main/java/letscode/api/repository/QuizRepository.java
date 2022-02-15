package letscode.api.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import letscode.api.entity.QuizEntity;
import letscode.api.helper.AuthHelper;

@Repository
@Transactional
public class QuizRepository extends BaseRepository<QuizEntity> {

	public QuizRepository() {
		super(QuizEntity.class);
	}

	public QuizEntity save(QuizEntity match) {
		return persist(match.getMatchId(), match);
	}

	public List<QuizEntity> getQuizzesFromMatch(String matchId) {
		TypedQuery<QuizEntity> query = query("SELECT q FROM quiz q WHERE q.matchId = :matchId AND q.userId = :userId");
		query.setParameter("matchId", matchId);
		query.setParameter("userId", AuthHelper.getUserLogged());

		return query.getResultList();
	}

	public QuizEntity getQuizById(String quizId) {
		var query = query("SELECT q FROM quiz q WHERE q.quizId = :quizId AND q.userId = :userId");
		query.setParameter("quizId", quizId);
		query.setParameter("userId", AuthHelper.getUserLogged());

		return getUnique(query);
	}

	public QuizEntity getActiveQuiz() {
		var query = query("SELECT q FROM quiz q WHERE q.answered IS NULL AND q.userId = :userId");
		query.setParameter("userId", AuthHelper.getUserLogged());

		return getUnique(query);
	}

	public QuizEntity searchMovieSet(String firstMovieId, String secondMovieId) {
		var query = query(
				"SELECT q FROM quiz q WHERE (q.firstMovieId = :firstMovieId AND q.secondMovieId = :secondMovieId) OR (q.firstMovieId = :secondMovieId AND q.secondMovieId = :firstMovieId) AND q.userId = :userId");
		query.setParameter("firstMovieId", firstMovieId);
		query.setParameter("secondMovieId", secondMovieId);
		query.setParameter("userId", AuthHelper.getUserLogged());

		return getUnique(query);
	}

	// lista para remover os quis no endGame
}