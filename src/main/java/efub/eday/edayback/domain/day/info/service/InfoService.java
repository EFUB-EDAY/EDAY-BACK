package efub.eday.edayback.domain.day.info.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import efub.eday.edayback.domain.day.info.entity.Info;
import efub.eday.edayback.domain.day.info.repository.InfoRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class InfoService {
	private final InfoRepository infoRepository;

	@Transactional(readOnly = true)
	public Info getInfoByDDay(int dDay) {
		return infoRepository.findByDDay_DDay(dDay)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 추가정보입니다."));
	}
}
