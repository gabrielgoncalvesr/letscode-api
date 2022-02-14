package letscode.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import letscode.api.enumeration.MatchStatusEnum;

@Entity(name = "match")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MatchEntity implements Serializable {

	private static final long serialVersionUID = -2722537103281723361L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", updatable = false, unique = true, nullable = false)
	private String matchId;

	@Column
	private String userId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", updatable = false, insertable = false, nullable = false)
	private UserEntity user;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MatchStatusEnum status;

	@Column(nullable = false)
	private int errorAttempts;

	@Column(nullable = false)
	private int score;

	@Column(nullable = false)
	private Date createDate;

	@Column
	private Date updateDate;

	@Column
	private Date endDate;

	public MatchEntity() {
	}

	public MatchEntity(String userId) {
		this.userId = userId;
		this.status = MatchStatusEnum.STARTED;
		this.errorAttempts = 0;
		this.score = 0;
		this.createDate = new Date();
		this.updateDate = null;
		this.endDate = null;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
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

	public MatchStatusEnum getStatus() {
		return status;
	}

	public void setStatus(MatchStatusEnum status) {
		this.status = status;
	}

	public int getErrorAttempts() {
		return errorAttempts;
	}

	public void setErrorAttempts(int errorAttempts) {
		this.errorAttempts = errorAttempts;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}