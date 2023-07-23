package efub.eday.edayback.domain.member.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import efub.eday.edayback.domain.day.title.entity.Title;
import efub.eday.edayback.domain.day.title.repository.TitleRepository;
import efub.eday.edayback.domain.member.auth.service.AuthService;
import efub.eday.edayback.domain.member.dto.ProfileDto;
import efub.eday.edayback.domain.member.dto.ProfileResDto;
import efub.eday.edayback.domain.member.dto.QuizDto;
import efub.eday.edayback.domain.member.dto.QuizListDto;
import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.member.entity.MemberTitle;
import efub.eday.edayback.domain.member.repository.MemberTitleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

	private final AuthService authService;
	private final MemberTitleRepository memberTitleRepository;
	private final TitleRepository titleRepository;

	@Transactional(readOnly = true)
	public ProfileResDto getMember() {
		Member member = authService.getCurrentMember();

		ProfileDto profile = new ProfileDto(member);
		Title title = titleRepository.findBySubjectId(profile.getDDay()).orElseThrow(IllegalArgumentException::new);
		profile.setTitleName(title.getName());

		// 현재 날짜와 데이터베이스에 저장된 날짜 사이의 차이 계산
		LocalDateTime currentDate = LocalDateTime.now();
		int differenceInDays = Math.toIntExact(ChronoUnit.DAYS.between(member.getCreatedDate(), currentDate));
		if (differenceInDays >= 7) {
			differenceInDays = 7;
		}

		//날짜가 같아 그러면 difference=0, openList는 dDay=7 하나
		List<QuizDto> openList = new ArrayList<>();
		for (int dDay = 7; dDay >= 7 - differenceInDays; dDay--) {
			openList.add(new QuizDto(dDay));
		}

		List<QuizDto> doneList = new ArrayList<>();
		List<MemberTitle> memberTitles = memberTitleRepository.findByMember(member);
		for (MemberTitle memberTitle : memberTitles) {
			if (memberTitle.getGetTitle()) {
				int dDay = memberTitle.getTitle().getSubject().getDday();
				doneList.add(new QuizDto(dDay));
			}
		}

		List<QuizDto> closeList = new ArrayList<>();
		for (int dDay = 6 - differenceInDays; dDay >= 1; dDay--) {
			closeList.add(new QuizDto(dDay));
		}

		QuizListDto quizListDto = new QuizListDto();
		quizListDto.setOpenList(openList);
		quizListDto.setDoneList(doneList);
		quizListDto.setCloseList(closeList);

		return new ProfileResDto(profile, quizListDto);
	}

}
