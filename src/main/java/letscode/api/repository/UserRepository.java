package letscode.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import letscode.api.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

	public Optional<UserEntity> findByEmail(String email);

}