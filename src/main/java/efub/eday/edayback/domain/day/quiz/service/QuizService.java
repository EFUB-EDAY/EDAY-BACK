package efub.eday.edayback.domain.day.quiz.service;
import efub.eday.edayback.domain.day.dday.entity.Subject;
import efub.eday.edayback.domain.day.quiz.entity.Options;
import efub.eday.edayback.domain.day.title.entity.Title;
import efub.eday.edayback.domain.day.title.repository.TitleRepository;
import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.member.entity.MemberTitle;
import efub.eday.edayback.domain.member.repository.MemberRepository;
import efub.eday.edayback.domain.member.repository.MemberTitleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import efub.eday.edayback.domain.day.dday.entity.Dday;
import efub.eday.edayback.domain.day.quiz.entity.Quiz;
import efub.eday.edayback.domain.day.quiz.repository.QuizRepository;
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
	public boolean checkAnswer(int d_day, int option_id, Long memberId){
		Quiz quiz = findQuiz(d_day);

		boolean isCorrect = false;
		for (Options option : quiz.getOptionsList()) {
			if (option.getOptionNumber() == option_id) {
				isCorrect = option.getIsAnswer();
				break;
			}
		}

		if(isCorrect){
			Member member = memberRepository.findById(memberId)
					.orElseThrow(()->new IllegalArgumentException("해당 멤버를 찾을 수 없습니다."));
			Long subjectId = quiz.getSubject().getId();
			Title title = titleRepository.findBySubjectId(subjectId)
					.orElseThrow(() -> new IllegalArgumentException("해당 subject_id에 대한 타이틀을 찾을 수 없습니다."));
			MemberTitle memberTitle = memberTitleRepository.findByMemberAndTitle(member, title)
					.orElseThrow(() -> new IllegalArgumentException("해당 멤버의 타이틀을 찾을 수 없습니다."));

			//getTitle값 true로 변경
			memberTitle.setGetTitle(true);
			memberTitleRepository.save(memberTitle);
		}
		return isCorrect;
	}

	//퀴즈 설명 가져오기
	public String getQuizDescription(int d_day) {
		Quiz quiz = findQuiz(d_day);
		return quiz.getDescription();
	}
}
