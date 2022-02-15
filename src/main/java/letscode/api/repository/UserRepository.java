package letscode.api.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import letscode.api.entity.UserEntity;
import letscode.api.helper.AuthHelper;

@Repository
@Transactional
public class UserRepository extends BaseRepository<UserEntity> {

	public UserRepository() {
		super(UserEntity.class);
	}

	public UserEntity save(UserEntity user) {
		return persist(user.getUserId(), user);
	}

	public UserEntity getByEmail(String email) {
		var query = query("SELECT u FROM user u WHERE u.email = :email");
		query.setParameter("email", email);

		return getUnique(query);
	}

	public UserEntity getUser() {
		var query = query("SELECT u FROM user u WHERE u.userId = :userId");
		query.setParameter("userId", AuthHelper.getUserLogged());

		return getUnique(query);
	}
}