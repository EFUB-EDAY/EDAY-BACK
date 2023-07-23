package efub.eday.edayback.domain.day.title.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping()
	public ResponseEntity<TitleResponseDto> getTitlePage() {
		TitleResponseDto titleResponseDto = titleService.getTitlePage();
		return ResponseEntity.ok(titleResponseDto);
	}
}
