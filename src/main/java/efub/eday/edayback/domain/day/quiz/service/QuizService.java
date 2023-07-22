package efub.eday.edayback.domain.day.quiz.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import efub.eday.edayback.domain.day.dday.entity.Dday;
import efub.eday.edayback.domain.day.quiz.entity.Quiz;
import efub.eday.edayback.domain.day.quiz.repository.QuizRepository;
import efub.eday.edayback.domain.day.title.entity.Title;
import efub.eday.edayback.domain.day.title.repository.TitleRepository;
import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.member.entity.MemberTitle;
import efub.eday.edayback.domain.member.repository.MemberRepository;
import efub.eday.edayback.domain.member.repository.MemberTitleRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizService {
	private final QuizRepository quizRepository;
	private final MemberRepository memberRepository;
	private final MemberTitleRepository memberTitleRepository;
	private final TitleRepository titleRepository;

	//퀴즈 내용 조회
	@Transactional(readOnly = true)
	public Quiz findQuiz(int d_day) {
		Dday dday = Dday.fromRemainingDays(d_day);
		return quizRepository.findBySubject_Dday(dday)
			.orElseThrow(() -> new IllegalArgumentException("퀴즈를 찾을 수 없습니다."));
	}

	//퀴즈 정답 확인
	public boolean checkAnswer(int d_day, int optionNumber, Long memberId) {
		Quiz quiz = findQuiz(d_day);

		boolean isCorrect = quiz.isAnswerOption(optionNumber);

		//member title 찾는 로직
		if (isCorrect) {
			Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다."));
			Integer subjectId = quiz.getSubject().getId();
			Title title = titleRepository.findBySubjectId(subjectId)
				.orElseThrow(() -> new IllegalArgumentException("해당 subject_id에 대한 타이틀을 찾을 수 없습니다."));
			MemberTitle memberTitle = memberTitleRepository.findByMemberAndTitle(member, title)
				.orElseThrow(() -> new IllegalArgumentException("해당 멤버의 타이틀을 찾을 수 없습니다."));

			//member lever+1
			if (!memberTitle.getGetTitle()) {
				member.setLevel(member.getLevel() + 1);
			}
			//getTitle값 true로 변경
			memberTitle.setGetTitle(true);
		}
		return isCorrect;
	}

	//퀴즈 설명 가져오기
	public String getQuizDescription(int d_day) {
		Quiz quiz = findQuiz(d_day);
		return quiz.getExplanation();
	}
}
