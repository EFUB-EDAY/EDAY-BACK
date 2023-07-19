package efub.eday.edayback.domain.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import efub.eday.edayback.domain.global.exception.CustomErrorCode;
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

			// 오류 내용 기입
			BaseResponse baseResponse = new BaseResponse(BaseResponseStatus.INVALID_JWT);

			Map<String, Object> errorDetails = setErrorDetails(baseResponse);

			sendErrorMessage(response, errorDetails);
		} catch (CustomException e) {
			log.error(e.getMessage());

			// 헤더에 토큰이 비어있거나 잘못된 정보가 기입되었을 경우
			if (e.getErrorCode().equals(CustomErrorCode.INVALID_AUTH)) {
				BaseResponse baseResponse = new BaseResponse(BaseResponseStatus.EMPTY_JWT);

				Map<String, Object> errorDetails = setErrorDetails(baseResponse);

				sendErrorMessage(response, errorDetails);
			}
		}
	}

	// Set Error Json
	private Map<String, Object> setErrorDetails(BaseResponse baseResponse) {
		Map<String, Object> errorDetails = new HashMap<>();

		errorDetails.put("isSuccess", baseResponse.getIsSuccess());
		errorDetails.put("code", baseResponse.getCode());
		errorDetails.put("message", baseResponse.getMessage());

		return errorDetails;
	}

	// Send Error Message to Client
	private void sendErrorMessage(HttpServletResponse response, Map<String, Object> errorDetails) throws IOException {

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");

		objectMapper.writeValue(response.getWriter(), errorDetails);
	}
}
