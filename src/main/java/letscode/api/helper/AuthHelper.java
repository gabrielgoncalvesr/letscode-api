package letscode.api.helper;

import java.text.ParseException;
import java.util.HashMap;

import org.springframework.security.core.context.SecurityContextHolder;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;

public class AuthHelper {

	public static JWTClaimsSet getClaims(String token) {
		try {
			JWT jwt = JWTParser.parse(token);

			return jwt.getJWTClaimsSet();
		} catch (ParseException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static String getUserLogged() {
		try {
			HashMap<String, Object> details = (HashMap<String, Object>) SecurityContextHolder.getContext()
					.getAuthentication().getDetails();

			if (details == null) {
				return null;
			}

			return (String) details.get("userId");
		} catch (Exception e) {
			return null;
		}
	}
}