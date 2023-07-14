package efub.eday.edayback.domain.day.info.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import efub.eday.edayback.domain.day.info.dto.InfoDto;
import efub.eday.edayback.domain.day.info.entity.Info;
import efub.eday.edayback.domain.day.info.service.InfoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/infos")
@RequiredArgsConstructor
public class InfoController {
	private final InfoService infoService;

	@GetMapping("/{d_day}")
	@ResponseStatus(value = HttpStatus.OK)
	public InfoDto getInfoByDDay(@PathVariable("d_day") int dDay) {
		Info info = infoService.getInfoByDDay(dDay);
		return InfoDto.from(info);
	}
}
