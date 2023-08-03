package efub.eday.edayback.domain.day.quiz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuizAnswerResponseDto {
    @Schema(description = "정답 여부")
    private boolean isAnswer;
    @Schema(description = "해당 퀴즈에 대한 설명")
    private String quizDescription;
    @Schema(description = "퀴즈 정답 해설 이미지")
    private String quizDescriptionImage;
    @Schema(description = "칭호 이미지")
    private String titleImage;

    public QuizAnswerResponseDto(boolean isAnswer, String quizDescription, String quizDescriptionImage, String titleImage) {
        this.isAnswer = isAnswer;
        this.quizDescription = quizDescription;
        this.quizDescriptionImage = quizDescriptionImage;
        this.titleImage = titleImage;
    }
}
