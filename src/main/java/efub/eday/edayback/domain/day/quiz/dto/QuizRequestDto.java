package efub.eday.edayback.domain.day.quiz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class QuizRequestDto {
    @Schema(description = "선지 번호", example = "1")
    private int optionNumber;
}
