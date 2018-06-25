package edu.usach.apicommons.util;

import edu.usach.apicommons.dto.TokenizableDTO;
import edu.usach.apicommons.model.ISecureEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

@SuppressWarnings("unchecked")
@Slf4j
public class SecurityUtils {

	private static final String SECRET = "BQSyS2Wbr7sfJ6SMDDVeUo4j1j8j5lYQwx3CNFfUTfuullMwPjfoC5QeD3r7O8kW";
	private static final long EXPIRATION_TIME = 864_000_000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";

	private SecurityUtils() throws IllegalAccessException {
		throw new IllegalAccessException("Utility class");
	}

	public static String tokenize(TokenizableDTO entity, String subject) {
		return Jwts.builder()
				.setClaims(JSONUtils.toJSONObject(entity))
				.setSubject(subject)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
				.compact();
	}

	public static Boolean isAuthenticated(String token) {
		log.info("Token: {}", token);
		if (token == null)
			return false;
		boolean success = "authenticated".equals(Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject());
		if (success)
			log.info("Authenticated!");
		return success;
	}

	public static Boolean isAuthorized(String token, String... roles) {
		if (!isAuthenticated(token))
			return false;
		Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token.replace(TOKEN_PREFIX,"")).getBody();
		for (String role : roles) {
			if (claims.get("roles", List.class).contains(role)) {
				return true;
			}
		}
		return false;
	}

	public static Boolean hasAccess(String token, ISecureEntity entity) {
		if (!isAuthenticated(token))
			return false;
		Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token.replace(TOKEN_PREFIX,"")).getBody();
		for (String role : entity.roleNames()) {
			if (claims.get("roles", List.class).contains(role)) {
				return true;
			}
		}
		return false;
	}

}
