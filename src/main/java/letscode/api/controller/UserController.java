package letscode.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import letscode.api.entity.UserEntity;
import letscode.api.repository.UserRepository;
import letscode.api.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public ResponseEntity<UserEntity> getUser() {
		var user = userRepository.getUser();

		return ResponseEntity.ok(user);
	}

	@PostMapping
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity model) {
		UserEntity user = userService.createUser(model);

		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
}