package letscode.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "quiz")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuizEntity implements Serializable {

	private static final long serialVersionUID = -2335587856573821264L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "quizId", updatable = false, unique = true, nullable = false)
	private String quizId;

	@Column
	private String firstMovieId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "firstMovieId", updatable = false, insertable = false, nullable = false)
	private MovieEntity firstMovie;

	@Column
	private String secondMovieId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "secondMovieId", updatable = false, insertable = false, nullable = false)
	private MovieEntity secondMovie;

	@Column
	private String userId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", updatable = false, insertable = false, nullable = false)
	private UserEntity user;

	@Column
	private String matchId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "matchId", updatable = false, insertable = false, nullable = false)
	private MatchEntity match;

	@Column
	private String correctOption;

	@Column
	private Boolean answered;

	@Column(nullable = true)
	private Boolean correct;

	@Column
	private Date createDate;

	@Column(nullable = true)
	private Date updateDate;

	public QuizEntity() {
	}

	public QuizEntity(String firstMovieId, String secondMovieId, String userId, String matchId, String correctOption) {
		this.firstMovieId = firstMovieId;
		this.secondMovieId = secondMovieId;
		this.userId = userId;
		this.matchId = matchId;
		this.correctOption = correctOption;
		this.answered = null;
		this.correct = null;
		this.createDate = new Date();
		this.updateDate = null;
	}

	public String getQuizId() {
		return quizId;
	}

	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}

	public String getFirstMovieId() {
		return firstMovieId;
	}

	public void setFirstMovieId(String firstMovieId) {
		this.firstMovieId = firstMovieId;
	}

	public MovieEntity getFirstMovie() {
		return firstMovie;
	}

	public void setFirstMovie(MovieEntity firstMovie) {
		this.firstMovie = firstMovie;
	}

	public String getSecondMovieId() {
		return secondMovieId;
	}

	public void setSecondMovieId(String secondMovieId) {
		this.secondMovieId = secondMovieId;
	}

	public MovieEntity getSecondMovie() {
		return secondMovie;
	}

	public void setSecondMovie(MovieEntity secondMovie) {
		this.secondMovie = secondMovie;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public MatchEntity getMatch() {
		return match;
	}

	public void setMatch(MatchEntity match) {
		this.match = match;
	}

	public String getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Boolean getAnswered() {
		return answered;
	}

	public void setAnswered(Boolean answered) {
		this.answered = answered;
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

}