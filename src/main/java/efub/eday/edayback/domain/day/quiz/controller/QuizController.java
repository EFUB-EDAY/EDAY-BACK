package efub.eday.edayback.domain.day.quiz.controller;

import efub.eday.edayback.domain.day.quiz.dto.QuizAnswerResponseDto;
import efub.eday.edayback.domain.day.quiz.dto.QuizRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import efub.eday.edayback.domain.day.quiz.dto.QuizResponseDto;
import efub.eday.edayback.domain.day.quiz.entity.Quiz;
import efub.eday.edayback.domain.day.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/quiz/{d_day}")
@RequiredArgsConstructor
public class QuizController {
	private final QuizService quizService;

	//퀴즈 내용 조회
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public QuizResponseDto findQuiz(@PathVariable int d_day) {
		Quiz quiz = quizService.findQuiz(d_day);
		return QuizResponseDto.from(quiz);
	}

	//퀴즈 정답 확인
	@PostMapping
	public QuizAnswerResponseDto checkAnswer(
			@PathVariable int d_day,
			@RequestBody QuizRequestDto quizRequestDto
	) {
		boolean isCorrect = quizService.checkAnswer(d_day, quizRequestDto.getOptionId());
		String quizDescription = null;
		if (isCorrect) {
			quizDescription = quizService.getQuizDescription(d_day);
		}
		QuizAnswerResponseDto responseDto = new QuizAnswerResponseDto(isCorrect, quizDescription);
		return responseDto;
	}
}
