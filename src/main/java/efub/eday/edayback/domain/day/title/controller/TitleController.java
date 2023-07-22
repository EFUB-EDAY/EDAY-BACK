package efub.eday.edayback.domain.day.title.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import efub.eday.edayback.domain.day.title.dto.TitleResponseDto;
import efub.eday.edayback.domain.day.title.service.TitleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/title")
@RequiredArgsConstructor
public class TitleController {

	private final TitleService titleService;

	@GetMapping("/{member_id}")
	public ResponseEntity<TitleResponseDto> getTitlePage(@PathVariable("member_id") Long memberId) {
		TitleResponseDto titleResponseDto = titleService.getTitlePage(memberId);
		return ResponseEntity.ok(titleResponseDto);
	}
}
