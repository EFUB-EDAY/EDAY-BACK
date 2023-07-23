package efub.eday.edayback.domain.day.quiz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import efub.eday.edayback.domain.day.quiz.dto.QuizAnswerResponseDto;
import efub.eday.edayback.domain.day.quiz.dto.QuizRequestDto;
import efub.eday.edayback.domain.day.quiz.dto.QuizResponseDto;
import efub.eday.edayback.domain.day.quiz.entity.Quiz;
import efub.eday.edayback.domain.day.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/quiz/{dDay}")
@RequiredArgsConstructor
public class QuizController {
	private final QuizService quizService;

	//퀴즈 내용 조회
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public QuizResponseDto findQuiz(@PathVariable int dDay) {
		Quiz quiz = quizService.findQuiz(dDay);
		return QuizResponseDto.from(quiz);
	}

	//퀴즈 정답 확인
	@PostMapping
	public QuizAnswerResponseDto checkAnswer(
		@PathVariable int dDay,
		@RequestBody QuizRequestDto quizRequestDto
	) {
		boolean isCorrect = quizService.checkAnswer(dDay, quizRequestDto.getOptionNumber());
		String quizDescription = null;
		if (isCorrect) {
			quizDescription = quizService.getQuizDescription(dDay);
		}
		return new QuizAnswerResponseDto(isCorrect, quizDescription);
	}
}
