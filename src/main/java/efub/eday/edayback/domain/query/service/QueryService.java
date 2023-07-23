package efub.eday.edayback.domain.query.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import efub.eday.edayback.domain.day.dday.entity.Dday;
import efub.eday.edayback.domain.day.dday.entity.Subject;
import efub.eday.edayback.domain.day.dday.repository.SubjectRepository;
import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.member.repository.MemberRepository;
import efub.eday.edayback.domain.query.dto.QueryRequestDto;
import efub.eday.edayback.domain.query.entity.Query;
import efub.eday.edayback.domain.query.repository.QueryRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class QueryService {
	private final QueryRepository queryRepository;
	private final MemberRepository memberRepository;
	private final SubjectRepository subjectRepository;

	public Query addQuery(QueryRequestDto requestDto) {
		Member writer = memberRepository.findById(requestDto.getMemberId())
			.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 ID입니다."));

		Dday dday = Dday.fromRemainingDays(requestDto.getDday());

		Subject subject = subjectRepository.findByDday(dday)
			.orElseThrow(() -> new IllegalArgumentException("해당하는 Subject가 없습니다. dday: " + dday.getRemainingDays()));

		Query query = Query.builder()
			.content(requestDto.getQueryContent())
			.writer(writer)
			.subject(subject)
			.build();

		return queryRepository.save(query);
	}
}
