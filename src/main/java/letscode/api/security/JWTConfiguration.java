package letscode.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import letscode.api.handler.AuthFailureHandler;
import letscode.api.service.UserDetailsServiceImpl;

@EnableWebSecurity
public class JWTConfiguration extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;

	private final UserDetailsServiceImpl userService;

	public JWTConfiguration(UserDetailsServiceImpl userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll().antMatchers("/h2-console/**")
				// .permitAll().anyRequest().authenticated();
				.permitAll().anyRequest().permitAll();

		http.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTValidationFilter(authenticationManager()));

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.headers().frameOptions().disable();
	}

	@Bean
	public AuthenticationFailureHandler getAuthFailureHandler() {
		return new AuthFailureHandler();
	}
}