package efub.eday.edayback.domain.day.quiz.service;

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

	//퀴즈 내용 조회
	@Transactional(readOnly = true)
	public Quiz findQuiz(int d_day) {
		Dday dday = Dday.fromRemainingDays(d_day);
		return quizRepository.findBySubject_Dday(dday)
			.orElseThrow(() -> new IllegalArgumentException("퀴즈를 찾을 수 없습니다."));
	}
}
