package efub.eday.edayback.domain.day.quiz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
