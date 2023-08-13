package efub.eday.edayback.domain.day.info.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import efub.eday.edayback.domain.day.dday.entity.Dday;
import efub.eday.edayback.domain.day.info.dto.InfoDto;
import efub.eday.edayback.domain.day.info.entity.Info;
import efub.eday.edayback.domain.day.info.service.InfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "추가정보", description = "추가정보 관련 api입니다.")
@RestController
@RequestMapping("/infos")
@RequiredArgsConstructor
public class InfoController {
	private final InfoService infoService;

	@Operation(summary = "추가정보 확인 메소드", description = "디데이 일수와 주제, 그리고 사진정보를 제공하는 메소드입니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "추가정보 확인 성공",
			content = @Content(mediaType = "application/json",
				schema = @Schema(implementation = InfoDto.class))),
		@ApiResponse(responseCode = "404", description = "추가정보 확인 실패(존재하지 않는 디데이)",
			content = @Content(mediaType = "application/json",
				schema = @Schema(implementation = InfoDto.class)))
	})
	@GetMapping("/{d_day}")
	@ResponseStatus(value = HttpStatus.OK)
	public InfoDto getInfoByDday(@PathVariable("d_day") int dday) {
		Dday ddayEnum = Dday.fromRemainingDays(dday);
		Info info = infoService.getInfoByDday(ddayEnum);
		return InfoDto.from(info);
	}
}
