package efub.eday.edayback.domain.member.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import efub.eday.edayback.domain.day.dday.entity.Dday;
import efub.eday.edayback.domain.day.title.repository.TitleRepository;
import efub.eday.edayback.domain.member.auth.service.AuthService;
import efub.eday.edayback.domain.member.dto.ProfileDto;
import efub.eday.edayback.domain.member.dto.ProfileResDto;
import efub.eday.edayback.domain.member.dto.QuizDto;
import efub.eday.edayback.domain.member.dto.QuizListDto;
import efub.eday.edayback.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

	private final AuthService authService;
	private final TitleRepository titleRepository;

	@Transactional(readOnly = true)
	public ProfileResDto getMember() {
		//현재 로그인 중인 사용자 정보 불러오기
		Member member = authService.getCurrentMember();

		// 열린 퀴즈 리스트
		LocalDate currentDate = LocalDate.now();
		long diff = ChronoUnit.DAYS.between(member.getCreatedDate(), currentDate);
		if (diff >= 7) {
			diff = 7;
		}
		int dayIndex = 7;
		List<QuizDto> openList = new ArrayList<>();
		for (; dayIndex >= 8 - diff; dayIndex--) {
			openList.add(new QuizDto(dayIndex));
		}

		// 완료된 퀴즈 리스트
		int level = member.getLevel();
		if (level > 7) {
			level = 7;
		}
		List<QuizDto> doneList = new ArrayList<>();
		int titleIndex = 7;
		for (; titleIndex >= 8 - level; titleIndex--) {
			doneList.add(new QuizDto(titleIndex));
		}

		// 잠긴 퀴즈 리스트
		List<QuizDto> closeList = new ArrayList<>();
		for (; dayIndex > 0; dayIndex--) {
			closeList.add(new QuizDto(dayIndex));
		}

		// 칭호
		String title = "";
		if (level > 0) {
			title = titleRepository.findBySubject_Dday(Dday.fromRemainingDays(titleIndex + 1))
				.orElseThrow(() ->
					new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR))
				.getName();
		}

		//profile부분에 들어갈 속성들
		ProfileDto profile = new ProfileDto(member.getNickname(),
			member.getProfileImageUrl(),
			member.getLevel(),
			title);

		QuizListDto quizListDto = new QuizListDto();
		quizListDto.setOpenList(openList);
		quizListDto.setDoneList(doneList);
		quizListDto.setCloseList(closeList);

		return new ProfileResDto(profile, quizListDto);
	}

}
