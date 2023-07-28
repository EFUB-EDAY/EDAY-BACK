package efub.eday.edayback.domain.member.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import efub.eday.edayback.domain.member.auth.dto.AuthRequestDto;
import efub.eday.edayback.domain.member.auth.dto.AuthResponseDto;
import efub.eday.edayback.domain.member.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "로그인", description = "로그인 관련 api입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member/auth")
public class AuthController {

	private final AuthService authService;

	@Operation(summary = "kakao에게 정보 요청 메소드", description = "로그인 정보를 바탕으로 kakao에 정보를 요청하는 메소드입니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "로그인 성공",
			content = @Content(mediaType = "application/json",
				schema = @Schema(implementation = AuthResponseDto.class))),
		@ApiResponse(responseCode = "404", description = "로그인 실패(인증되지 않은 사용자)",
			content = @Content(mediaType = "application/json",
				schema = @Schema(implementation = AuthResponseDto.class)))
	})
	@PostMapping
	public AuthResponseDto login(@RequestBody AuthRequestDto authRequestDto) {
		return authService.signIn(authRequestDto.getCode());
	}

}
