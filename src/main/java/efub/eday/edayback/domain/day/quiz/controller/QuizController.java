package efub.eday.edayback.domain.day.quiz.controller;

import efub.eday.edayback.domain.day.quiz.dto.QuizRequestDto;
import efub.eday.edayback.domain.day.quiz.dto.QuizResponseDto;
import efub.eday.edayback.domain.day.quiz.entity.Quiz;
import efub.eday.edayback.domain.day.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz/{d_day}")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    //퀴즈 내용 조회
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public QuizResponseDto quizFind(@PathVariable Long d_day){
        Quiz quiz = quizService.findQuiz(d_day);
        return QuizResponseDto.from(quiz);
    }
}
