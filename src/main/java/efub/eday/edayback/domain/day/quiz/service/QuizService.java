package efub.eday.edayback.domain.day.quiz.service;

import efub.eday.edayback.domain.day.quiz.entity.Options;
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

	//퀴즈 정답 확인
	public boolean checkAnswer(int d_day, int option_id){
		Quiz quiz = findQuiz(d_day);

		for (Options option : quiz.getOptionsList()) {
			if (option.getOptionNumber() == option_id) {
				return option.getIsAnswer();
			}
		}
		throw new IllegalArgumentException("올바르지 않은 optionId입니다.");
	}

	//퀴즈 설명 가져오기
	public String getQuizDescription(int d_day) {
		Quiz quiz = findQuiz(d_day);
		return quiz.getDescription();
	}
}
