package letscode.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "ranking")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RankingEntity implements Serializable {

	private static final long serialVersionUID = 129022155281857923L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "rankingId", updatable = false, unique = true, nullable = false)
	private String rankingId;

	@Column
	private String userId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", updatable = false, insertable = false, nullable = false)
	private UserEntity user;

	@Column(nullable = false)
	private double score;

	@Column(nullable = false)
	private Date createDate;

	@Column
	private Date updateDate;

	public RankingEntity() {
	}

	public RankingEntity(String userId, double score) {
		this.userId = userId;
		this.score = score;
		this.createDate = new Date();
		this.updateDate = null;
	}

	public String getRankingId() {
		return rankingId;
	}

	public void setRankingId(String rankingId) {
		this.rankingId = rankingId;
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

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
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
}