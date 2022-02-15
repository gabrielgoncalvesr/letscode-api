package letscode.api.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nimbusds.jwt.JWTClaimsSet;

import letscode.api.helper.AuthHelper;

public class JWTValidationFilter extends BasicAuthenticationFilter {

	public JWTValidationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String token = request.getHeader("Authorization");

		if (token == null) {
			chain.doFilter(request, response);
			return;
		}

		if (!token.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}

		String user = JWT.require(Algorithm.HMAC512(JWTAuthenticationFilter.SECRET_TOKEN)).build()
				.verify(token.replaceAll("Bearer ", "")).getSubject();

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null,
				new ArrayList<>());

		SecurityContextHolder.getContext().setAuthentication(authenticationToken);

		JWTClaimsSet claims = AuthHelper.getClaims(token.replaceAll("Bearer ", ""));
		String userId = (String) claims.getClaim("userId");

		SecurityContext securityContext = SecurityContextHolder.getContext();
		AbstractAuthenticationToken auth = (AbstractAuthenticationToken) securityContext.getAuthentication();

		HashMap<String, Object> details = new HashMap<String, Object>();
		details.put("userId", userId);
		auth.setDetails(details);

		chain.doFilter(request, response);
	}

}