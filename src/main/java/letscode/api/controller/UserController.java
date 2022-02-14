package letscode.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import letscode.api.entity.UserEntity;
import letscode.api.exception.ResponseException;
import letscode.api.model.request.ValidateQuizModelRequest;
import letscode.api.repository.UserRepository;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping
	public ResponseEntity createUser(@RequestBody UserEntity userEntity) {

		Optional<UserEntity> user = userRepository.findByEmail(userEntity.getEmail());

		if (user.isPresent()) {
			throw new ResponseException(HttpStatus.CONFLICT, "user.email_registered");
		}

		var encodedPassword = passwordEncoder.encode(userEntity.getPassword());
		userEntity.setPassword(encodedPassword);

		userRepository.save(userEntity);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}