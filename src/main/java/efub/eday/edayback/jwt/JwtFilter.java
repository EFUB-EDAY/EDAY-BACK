package efub.eday.edayback.jwt;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

	private static final String BEARER = "Bearer";
	private static final String BLANK = " ";
	private static final String USER_ROLE = "USER";

	private final JwtProvider jwtProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		log.debug("authorization : {}", authorization);
		log.debug(request.getServletPath());

		if (authorization == null || !authorization.startsWith(BEARER)) {
			SecurityContextHolder.getContext().setAuthentication(null);
			filterChain.doFilter(request, response);
			return;
		}

		// Token 꺼내기
		String token = authorization.split(BLANK)[1];

		// Token Expired 되었는지 여부
		if (jwtProvider.isExpired(token)) {
			filterChain.doFilter(request, response);
			return;
		}

		// memberId Token에서 꺼내기
		Long memberId = jwtProvider.getMemberId(token);
		log.debug("memberId: {}", memberId);
		log.debug("isAccess: {} / token: {}", jwtProvider.isAccessToken(token), token);

		// 토큰 재발급일 경우 리프레쉬 토큰 확인
		// 위에서 만료됐는지 확인했기 때문에 따로 만료확인 필요 없음
		// 리프레쉬 토큰이 유효한지와 path 정보를 통해 확인이 끝났기 때문에 컨트롤러에서는 바로 토큰 재발행해주고 보내주면 됨
		// if (
		// 	!(
		// 		(path.startsWith("토큰 재발행 API") && jwtProvider.isRefreshToken(token))
		// 			|| jwtProvider.isAccessToken(token)
		// 	)
		// ) {
		// 	// 재발행 요청 api인데, access token을 전달했을 경우
		// 	// 아니면 access token을 넣어줘야하는데, 다른 토큰을 넣었을 경우
		// 	throw new JwtException("");
		// }

		// 권한 부여
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
			memberId,
			null,
			List.of(new SimpleGrantedAuthority(USER_ROLE)));

		// Detail을 넣어줌
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		log.debug("[+] Token in SecurityContextHolder");

		filterChain.doFilter(request, response);
	}
}
