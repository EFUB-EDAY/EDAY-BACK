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
	private final TitleRepository titleRepository;
	private final MemberTitleRepository memberTitleRepository;

	@Transactional(readOnly = true)
	public ProfileResDto getMember() {
		//현재 로그인 중인 사용자 정보 불러오기
		Member member = authService.getCurrentMember();

		int level = member.getLevel();
		if (level > 7) {
			level = 7;
		}

		//칭호는 dDay가 아닌 level을 기준으로 정해짐
		Title title = titleRepository.findBySubjectId(level).orElseThrow();

		//profile부분에 들어갈 속성들
		ProfileDto profile = new ProfileDto(member.getNickname(),
			member.getProfileImageUrl(),
			member.getLevel(),
			title.getName());

		// 현재 날짜와 데이터베이스에 저장된 날짜 사이의 차이 계산. 날짜 차이는 6일이 최대(날짜 차이가 6이면 D-1임)
		LocalDateTime currentDate = LocalDateTime.now();
		int differenceInDays = Math.toIntExact(
			ChronoUnit.DAYS.between(member.getCreatedDate().toLocalDate(), currentDate.toLocalDate()));
		if (differenceInDays > 6) {
			differenceInDays = 6;
		}

		//날짜가 같아 그러면 difference=0, openList는 dDay=7 하나
		List<QuizDto> openList = new ArrayList<>();
		for (int day = 7; day >= 7 - differenceInDays; day--) {
			openList.add(new QuizDto(day));
		}

		//doneList는 memberTitle테이블에 1로 된(푼 처리된) 애들 리스트를 불러옴
		List<QuizDto> doneList = new ArrayList<>();
		List<MemberTitle> memberTitles = memberTitleRepository.findByMember(member);
		for (MemberTitle memberTitle : memberTitles) {
			if (memberTitle.getGetTitle()) {
				int subject = memberTitle.getTitle().getSubject().getDday();
				doneList.add(new QuizDto(subject));
			}
		}

		List<QuizDto> closeList = new ArrayList<>();
		for (int day = 6 - differenceInDays; day >= 1; day--) {
			closeList.add(new QuizDto(day));
		}

		QuizListDto quizListDto = new QuizListDto();
		quizListDto.setOpenList(openList);
		quizListDto.setDoneList(doneList);
		quizListDto.setCloseList(closeList);

		return new ProfileResDto(profile, quizListDto);
	}

}
