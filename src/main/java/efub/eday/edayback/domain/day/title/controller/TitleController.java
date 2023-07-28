package efub.eday.edayback.domain.day.title.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "칭호 페이지", description = "칭호 페이지 관련 api입니다.")
public class TitleController {

	private final TitleService titleService;

	@Operation(summary = "칭호 페이지 조회", description = "칭호 페이지를 조회하는 메서드입니다.")
	@ApiResponse(responseCode = "200", description = "칭호 페이지 조회 성공",
			   content = @Content(mediaType = "application/json",
			   schema = @Schema(implementation = TitleResponseDto.class)))
	@GetMapping()
	public ResponseEntity<TitleResponseDto> getTitlePage() {
		TitleResponseDto titleResponseDto = titleService.getTitlePage();
		return ResponseEntity.ok(titleResponseDto);
	}
}
