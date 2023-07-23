package efub.eday.edayback.domain.day.title.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import efub.eday.edayback.domain.day.title.dto.TitleResponseDto;
import efub.eday.edayback.domain.day.title.service.TitleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/title")
@RequiredArgsConstructor
public class TitleController {

	private final TitleService titleService;

	@GetMapping()
	public ResponseEntity<TitleResponseDto> getTitlePage() {
		try {
			TitleResponseDto titleResponseDto = titleService.getTitlePage();
			return ResponseEntity.ok(titleResponseDto);
		} catch (IllegalArgumentException e) {
			// 해당하는 Subject가 없는 경우 404 상태 코드와 에러 메시지를 반환
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 Subject가 없습니다.");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "서버에서 오류가 발생했습니다.");
		}
	}
}
