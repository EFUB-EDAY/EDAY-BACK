package efub.eday.edayback.domain.query.controller;

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
public class QueryController {
	private final QueryService queryService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public Query addquery(@RequestBody QueryRequestDto requestDto) {
		return queryService.addQuery(requestDto);
	}
}
