package efub.eday.edayback.domain.query.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import efub.eday.edayback.domain.day.dday.entity.Subject;
import efub.eday.edayback.domain.day.dday.repository.SubjectRepository;
import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.member.service.MemberService;
import efub.eday.edayback.domain.query.dto.QueryRequestDto;
import efub.eday.edayback.domain.query.entity.Query;
import efub.eday.edayback.domain.query.repository.QueryRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class QueryService {
	private final QueryRepository queryRepository;
	private final MemberService memberService;
	private final SubjectRepository subjectRepository;

	public Query addQuery(QueryRequestDto requestDto) {
		Member writer = memberService.findMemberById(requestDto.getMemberId());

		Subject subject = subjectRepository.findById(requestDto.getSubjectId())
			.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 ID"));

		Query query = Query.builder()
			.content(requestDto.getQueryContent())
			.writer(writer)
			.subject(subject)
			.build();

		return queryRepository.save(query);
	}
}
