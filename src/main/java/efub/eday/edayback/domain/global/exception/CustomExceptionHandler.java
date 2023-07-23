package efub.eday.edayback.domain.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<CustomErrorResponse> handleCustomException(CustomException e) {
		log.error("[handleCustomException] {} : {}", e.getErrorCode().name(), e.getErrorCode().getMessage());
		return CustomErrorResponse.error(e);
	}
}
