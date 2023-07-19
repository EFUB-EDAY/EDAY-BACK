package efub.eday.edayback.domain.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import efub.eday.edayback.domain.member.dto.LoginResponseDto;
import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
	private final MemberService memberService;

	@GetMapping("/oauth")
	public LoginResponseDto kakaoLogin(@RequestParam String code) {
		System.out.println("HI");
		Member member = memberService.getAccessToken(code);
		System.out.println(member);
		return memberService.login(member);
	}

}
