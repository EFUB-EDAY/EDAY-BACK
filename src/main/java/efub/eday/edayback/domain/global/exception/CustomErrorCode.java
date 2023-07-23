package efub.eday.edayback.domain.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode {
	SUCCESS(HttpStatus.OK, "OK"),

	INVALID_AUTH(HttpStatus.BAD_REQUEST, "JWT를 입력해주세요.");

	private final HttpStatus status;
	private final String message;
}
