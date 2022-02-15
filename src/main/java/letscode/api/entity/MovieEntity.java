package letscode.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "movie")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MovieEntity implements Serializable {

	private static final long serialVersionUID = 18688269488082000L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "movieId", updatable = false, unique = true, nullable = false)
	private String movieId;

	@Column
	private String imdbId;

	@Column
	private String title;

	public MovieEntity() {
	}

	public MovieEntity(String imdbId, String title) {
		this.imdbId = imdbId;
		this.title = title;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}