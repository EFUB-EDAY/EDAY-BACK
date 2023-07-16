package efub.eday.edayback.domain.query.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import efub.eday.edayback.domain.query.dto.QueryRequestDto;
import efub.eday.edayback.domain.query.entity.Query;
import efub.eday.edayback.domain.query.repository.QueryRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class QueryService {

	private final QueryRepository queryRepository;
	// private final MemberService memberService;

	public Query addQuery(QueryRequestDto requestDto) {
		// Member writer = memberService.findMemberById(requestDto.getMemberId());

		/*
		Subject subject = Subject.builder()
			.dday(Dday.valueOf(requestDto.getDDay()))
			.build();
		*/

		Query query = Query.builder()
			.content(requestDto.getQueryContent())
			//	.writer(writer)
			//	.subject(subject)
			.build();

		return queryRepository.save(query);
	}
}
