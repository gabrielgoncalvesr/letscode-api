package letscode.api.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import letscode.api.data.UserDetailsData;
import letscode.api.entity.UserEntity;
import letscode.api.exception.ResponseException;
import letscode.api.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> user = userRepository.findByEmail(username);
		if(user.isEmpty()) {
			throw new ResponseException(HttpStatus.NOT_FOUND, "user.not_found");
		}
		
		return new UserDetailsData(user);
	}

}
