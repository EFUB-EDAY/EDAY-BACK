package efub.eday.edayback.domain.day.title.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import efub.eday.edayback.domain.day.dday.entity.Dday;
import efub.eday.edayback.domain.day.title.dto.TitleDto;
import efub.eday.edayback.domain.day.title.dto.TitleResponseDto;
import efub.eday.edayback.domain.day.title.service.TitleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

	@Operation(summary = "특정 디데이에 해당하는 칭호 정보 조회", description = "특정 디데이(d_day)에 해당하는 칭호 정보를 조회하는 메서드입니다.")
	@ApiResponse(responseCode = "200", description = "칭호 정보 조회 성공",
		content = @Content(mediaType = "application/json",
			schema = @Schema(implementation = TitleDto.class)))
	@GetMapping("/{d_day}")
	public ResponseEntity<TitleDto> getTitleByDday(@PathVariable("d_day") int dday) {
		Dday ddayEnum = Dday.fromRemainingDays(dday);
		TitleDto titleDto = titleService.getTitleByDday(ddayEnum);
		return ResponseEntity.ok(titleDto);
	}
}
