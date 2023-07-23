package efub.eday.edayback.domain.member.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import efub.eday.edayback.domain.member.auth.dto.AuthRequestDto;
import efub.eday.edayback.domain.member.auth.dto.AuthResponseDto;
import efub.eday.edayback.domain.member.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping
	public AuthResponseDto login(@RequestBody AuthRequestDto authRequestDto) {
		return authService.signIn(authRequestDto.getCode());
	}

}
