package letscode.api.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import letscode.api.entity.UserEntity;

public class UserDetailsData implements UserDetails {

	private static final long serialVersionUID = 6227121150391086577L;

	private final Optional<UserEntity> user;

	public UserDetailsData(Optional<UserEntity> user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return user.orElse(new UserEntity()).getPassword();
	}

	@Override
	public String getUsername() {
		return user.orElse(new UserEntity()).getEmail();
	}

	public String getUserId() {
		return user.orElse(new UserEntity()).getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
