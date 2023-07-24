package efub.eday.edayback.domain.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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
	private final TitleRepository titleRepository;
	private final MemberTitleRepository memberTitleRepository;

	//@Transactional(readOnly = true)
	public ProfileResDto getMember() {
		//현재 로그인 중인 사용자 정보 불러오기
		Member member = authService.getCurrentMember();

		int level = member.getLevel();

		//칭호는 dDay가 아닌 level을 기준으로 정해짐
		Title title = titleRepository.findBySubjectId(level).orElseThrow();

		//profile부분에 들어갈 속성들
		ProfileDto profile = new ProfileDto(member.getNickname(),
			member.getProfileImageUrl(),
			member.getLevel(),
			title.getName());

		/*
		// 현재 날짜와 데이터베이스에 저장된 날짜 사이의 차이 계산
		LocalDateTime currentDate = LocalDateTime.now();
		int differenceInDays = Math.toIntExact(ChronoUnit.DAYS.between(member.getCreatedDate(), currentDate));
		if (differenceInDays >= 7) {
			differenceInDays = 7;
		}
		*/
		int differenceInDays = 2;

		//날짜가 같아 그러면 difference=0, openList는 dDay=7 하나
		List<QuizDto> openList = new ArrayList<>();
		for (int dDay = 7; dDay >= 7 - differenceInDays; dDay--) {
			openList.add(new QuizDto(dDay));
		}

		List<QuizDto> doneList = new ArrayList<>();
		List<MemberTitle> memberTitles = memberTitleRepository.findByMember(member);
		for (MemberTitle memberTitle : memberTitles) {
			if (memberTitle.getGetTitle()) {
				int subject = memberTitle.getTitle().getSubject().getDday();
				doneList.add(new QuizDto(subject));
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
