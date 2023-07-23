package efub.eday.edayback.domain.day.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuizAnswerResponseDto {
    private boolean isAnswer;
    private String quizDescription;

    public QuizAnswerResponseDto(boolean isAnswer, String quizDescription) {
        this.isAnswer = isAnswer;
        this.quizDescription = quizDescription;
    }
}
