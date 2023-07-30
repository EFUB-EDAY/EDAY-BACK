package efub.eday.edayback.domain.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import efub.eday.edayback.domain.query.dto.QueryRequestDto;
import efub.eday.edayback.domain.query.entity.Query;
import efub.eday.edayback.domain.query.service.QueryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/querys")
@RequiredArgsConstructor
@Tag(name = "문의", description = "문의사항 관련 api입니다")
public class QueryController {
	private final QueryService queryService;

	@Operation(summary = "문의사항 추가 메서드", description = "새로운 문의사항을 추가하는 메서드 입니다")
	@ApiResponse(responseCode = "201", description = "문의사항 추가 성공",
			content = @Content(mediaType = "application/json",
					schema = @Schema(implementation = Query.class)))
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Query addquery(@RequestBody QueryRequestDto requestDto) {
		return queryService.addQuery(requestDto);
	}
}
