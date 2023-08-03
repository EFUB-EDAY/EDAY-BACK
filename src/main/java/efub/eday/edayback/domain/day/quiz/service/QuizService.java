package efub.eday.edayback.domain.day.quiz.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import efub.eday.edayback.domain.day.dday.entity.Dday;
import efub.eday.edayback.domain.day.quiz.entity.Quiz;
import efub.eday.edayback.domain.day.quiz.repository.QuizRepository;
import efub.eday.edayback.domain.day.title.entity.Title;
import efub.eday.edayback.domain.day.title.repository.TitleRepository;
import efub.eday.edayback.domain.member.auth.service.AuthService;
import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.member.entity.MemberQuiz;
import efub.eday.edayback.domain.member.entity.MemberTitle;
import efub.eday.edayback.domain.member.repository.MemberQuizRepository;
import efub.eday.edayback.domain.member.repository.MemberTitleRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizService {
	private final QuizRepository quizRepository;
	private final MemberTitleRepository memberTitleRepository;
	private final TitleRepository titleRepository;
	private final MemberQuizRepository memberQuizRepository;
	private final AuthService authService;

	//퀴즈 내용 조회
	@Transactional(readOnly = true)
	public Quiz findQuiz(int d_day) {
		Dday dday = Dday.fromRemainingDays(d_day);
		return quizRepository.findBySubject_Dday(dday)
			.orElseThrow(() -> new IllegalArgumentException("해당 날짜의 퀴즈를 찾을 수 없습니다."));
	}

	//퀴즈 정답 확인
	public boolean checkAnswer(int d_day, int optionNumber) {
		Quiz quiz = findQuiz(d_day);

		boolean isCorrect = quiz.isAnswerOption(optionNumber);

		if (isCorrect) {
			Member member = authService.getCurrentMember();
			Integer subjectId = quiz.getSubject().getId();
			Title title = titleRepository.findBySubjectId(subjectId)
				.orElseThrow(() -> new IllegalArgumentException("해당 subject_id에 대한 타이틀을 찾을 수 없습니다."));
			MemberTitle memberTitle = memberTitleRepository.findByMemberAndTitle(member, title)
				.orElseThrow(() -> new IllegalArgumentException("해당 멤버 타이틀을 찾을 수 없습니다."));
			MemberQuiz memberQuiz = memberQuizRepository.findByMemberAndQuiz(member, quiz)
				.orElseThrow(() -> new IllegalArgumentException("해당 멤버 퀴즈를 찾을 수 없습니다."));

			//member level+1
			if (!memberTitle.getGetTitle()) {
				member.setLevel(member.getLevel() + 1);
			}
			//MemberTitle의 getTitle값 true로 변경
			memberTitle.setGetTitle(true);

			//MemberQuiz의 isCorrect 값 true로 변경
			memberQuiz.setIsCorrect(true);

		}
		return isCorrect;
	}

	//퀴즈 설명 가져오기
	public String getQuizDescription(int d_day) {
		Quiz quiz = findQuiz(d_day);
		return quiz.getExplanation();
	}

	//퀴즈 설명 이미지 가져오기
	public String getDescriptionImg(int d_day){
		Quiz quiz = findQuiz(d_day);
		return quiz.getImageUrl();
	}
}
