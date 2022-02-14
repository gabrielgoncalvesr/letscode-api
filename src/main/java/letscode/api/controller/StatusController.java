package letscode.api.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import letscode.api.entity.UserEntity;
import letscode.api.exception.ResponseException;
import letscode.api.helper.AuthHelper;
import letscode.api.repository.UserRepository;

@RestController
public class StatusController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/api/check")
	public Object check(HttpServletRequest request) {

		String userId = AuthHelper.getUserLogged();

		return userId;

//		var authentication = SecurityContextHolder.getContext().getAuthentication();
//
//		String token = new DefaultBearerTokenResolver().resolve((HttpServletRequest) request);
//		try {
//			JWT jwt = JWTParser.parse(token);
//
//			return jwt.getJWTClaimsSet();
//
//		} catch (ParseException e) {
//
//		}
//
//		return null;
	}

	@GetMapping("/api/user/{userId}")
	public ResponseEntity getUserById(@PathVariable("userId") String userId) {
		Optional<UserEntity> user = userRepository.findById(userId);

		if (user.isEmpty()) {
			throw new ResponseException(HttpStatus.NOT_FOUND, "user.not_found");
		}

		return ResponseEntity.ok(user);
	}

}