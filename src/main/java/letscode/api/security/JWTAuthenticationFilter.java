package letscode.api.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import letscode.api.data.UserDetailsData;
import letscode.api.entity.UserEntity;
import letscode.api.model.response.AuthModelResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public static final int EXPIRATION_TOKEN = 600_000;

	public static final String SECRET_TOKEN = "b6a007dfb313ae949823050a103fdc0073fc017ac58fbef8fd86b9125c834bb1d7b0dbb75a17d25473a2680a78507d9e";

	private final AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			UserEntity user = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			// throw new UnauthorizedException("user.auth_error", "error to authenticate
			// user");
			throw new RuntimeException("failed");
		}
	}

	@Override
	public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		UserDetailsData userData = (UserDetailsData) authResult.getPrincipal();

		String token = JWT.create().withSubject(userData.getUsername())
				.withClaim("userId", userData.getUserId().toString())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN))
				.sign(Algorithm.HMAC512(SECRET_TOKEN));

		AuthModelResponse responseModel = new AuthModelResponse(userData.getUsername(), token);

		ObjectMapper mapper = new ObjectMapper();

		var responseAsString = mapper.writeValueAsString(responseModel);

		PrintWriter out = response.getWriter();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		out.print(responseAsString);
		out.flush();
	}
}