package efub.eday.edayback.domain.day.info.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import efub.eday.edayback.domain.day.dday.entity.Dday;
import efub.eday.edayback.domain.day.info.entity.Info;
import efub.eday.edayback.domain.day.info.repository.InfoRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class InfoService {
	private final InfoRepository infoRepository;

	@Transactional(readOnly = true)
	public Info getInfoByDday(Dday dday) {
		return infoRepository.findInfoBySubject_Dday(dday)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 추가정보입니다."));
	}
}
