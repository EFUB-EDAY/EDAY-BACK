package efub.eday.edayback.domain.query.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import efub.eday.edayback.domain.member.auth.service.AuthService;
import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.query.dto.QueryRequestDto;
import efub.eday.edayback.domain.query.entity.Query;
import efub.eday.edayback.domain.query.service.QueryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/querys")
@RequiredArgsConstructor
public class QueryController {
	private final QueryService queryService;
	private final AuthService authService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Query addquery(@RequestBody QueryRequestDto requestDto) {
		Member writer = authService.getCurrentMember(); // 로그인된 유저 정보 가져오기
		requestDto.setMemberId(writer.getId()); // 가져온 유저 정보의 ID를 QueryRequestDto에 설정
		return queryService.addQuery(requestDto);
	}
}
