package efub.eday.edayback.domain.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import efub.eday.edayback.domain.member.dto.ProfileResDto;
import efub.eday.edayback.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "회원", description = "회원정보 관련 api입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;

	@Operation(summary = "회원정보 조회 메소드", description = "현재 로그인 중인 회원정보를 제공하는 메소드입니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "회원정보 조회 성공",
			content = @Content(mediaType = "application/json",
				schema = @Schema(implementation = ProfileResDto.class)))
	})
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public ProfileResDto getMember() {
		return memberService.getMember();
	}

}
