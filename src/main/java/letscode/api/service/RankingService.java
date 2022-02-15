package letscode.api.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import letscode.api.entity.RankingEntity;
import letscode.api.helper.AuthHelper;
import letscode.api.repository.QuizRepository;
import letscode.api.repository.RankingRepository;

@Service
public class RankingService {

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private RankingRepository rankingRepository;

	public RankingEntity updateUserRanking(String matchId) {
		var quizList = quizRepository.getQuizzesFromMatch(matchId);

		int totalQuizzes = quizList.size();
		int totalHits = quizList.stream().filter(x -> x.getCorrect() != null && x.getCorrect()).toList().size();

		double score = (totalHits * 100) / totalQuizzes;

		RankingEntity ranking = rankingRepository.getUserRanking(AuthHelper.getUserLogged());
		if (ranking == null) {
			ranking = new RankingEntity(AuthHelper.getUserLogged(), score);
		} else {
			if (score > ranking.getScore()) {
				ranking.setScore(score);
				ranking.setUpdateDate(new Date());
			}
		}

		rankingRepository.save(ranking);

		return ranking;
	}
}