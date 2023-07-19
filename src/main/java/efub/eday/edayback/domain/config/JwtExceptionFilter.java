package efub.eday.edayback.domain.config;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import efub.eday.edayback.domain.global.exception.CustomException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {
	private final ObjectMapper objectMapper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws
		ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (JwtException e) {
			log.error("[-] Invalid Token");

		} catch (CustomException e) {
			log.error(e.getMessage());

		}
	}

}
