package efub.eday.edayback.domain.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
	private final MemberService memberService;

	@GetMapping("/oauth")
	public @ResponseBody String kakaoLogin(String code) {
		Member member = memberService.getAccessToken(code);
		return "카카오 인증 완료: " + code;
	}

}
