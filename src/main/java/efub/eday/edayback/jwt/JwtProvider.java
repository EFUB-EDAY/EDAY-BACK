package efub.eday.edayback.jwt;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class JwtProvider {
	private static final Long ACCESS_TOKEN_VALID_TIME = Duration.ofMinutes(30).toMillis(); // 만료시간 30분
	private static final Long REFRESH_TOKEN_VALID_TIME = Duration.ofDays(14).toMillis(); // 만료시간 2주
	private static final String MEMBER_ID = "memberId";
	private static final String TOKEN_TYPE = "type";

	@Value("${jwt.secret}")
	private String secretKey;

	@PostConstruct
	private void initSecretKey() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	// JWT 생성
	private String createJwt(Long memberId, String type, Long tokenValidTime) {
		Claims claims = Jwts.claims();
		claims.put(MEMBER_ID, memberId);

		return Jwts.builder()
			.setHeaderParam("type", type)
			.setClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + tokenValidTime))
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}

	// access 토큰 생성
	public String createAccessToken(Long memberId) {
		return createJwt(memberId, TokenType.REFRESH.type, ACCESS_TOKEN_VALID_TIME);
	}

	// refresh 토큰 생성
	public String createRefreshToken(Long memberId) {
		return createJwt(memberId, TokenType.REFRESH.type, REFRESH_TOKEN_VALID_TIME);
	}

	// 회원 정보 조회
	public Long getMemberId(String token) {
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody()
			.get(MEMBER_ID, Long.class);
	}

	// 토큰 유효 및 만료 확인
	public boolean isExpired(String token) {
		try {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return claimsJws.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	// refresh 토큰 확인
	public boolean isRefreshToken(String token) {
		String tokenType = Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getHeader()
			.get(TOKEN_TYPE).toString();
		return tokenType.equals(TokenType.REFRESH.type);
	}

	// access 토큰 확인
	public boolean isAccessToken(String token) {
		String tokenType = Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getHeader()
			.get(TOKEN_TYPE).toString();
		return tokenType.equals(TokenType.ACCESS.type);
	}

	private enum TokenType {
		ACCESS("access"),
		REFRESH("refresh");

		private final String type;

		TokenType(String type) {
			this.type = type;
		}
	}

}
