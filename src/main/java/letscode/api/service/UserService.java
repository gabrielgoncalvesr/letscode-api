package letscode.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import letscode.api.entity.UserEntity;
import letscode.api.exception.ResponseException;
import letscode.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserEntity createUser(UserEntity model) {
		UserEntity user = userRepository.getByEmail(model.getEmail());
		if (user != null) {
			throw new ResponseException(HttpStatus.CONFLICT, "user.email_registered");
		}

		var encodedPassword = passwordEncoder.encode(model.getPassword());
		model.setPassword(encodedPassword);

		return userRepository.save(model);
	}
}