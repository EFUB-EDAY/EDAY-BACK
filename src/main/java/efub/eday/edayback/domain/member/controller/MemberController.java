package efub.eday.edayback.domain.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import efub.eday.edayback.domain.member.dto.MemberResponseDto;
import efub.eday.edayback.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
	private final MemberService memberService;

	@GetMapping("/oauth")
	public MemberResponseDto kakaoLogin(String code) {
		MemberResponseDto member = memberService.getAccessToken(code);
		return member;
	}

}
