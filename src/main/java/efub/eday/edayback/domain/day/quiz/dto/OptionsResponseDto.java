package efub.eday.edayback.domain.day.quiz.dto;

import efub.eday.edayback.domain.day.quiz.entity.Options;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OptionsResponseDto {
    private String content;

    private OptionsResponseDto(String content){
        this.content=content;
    }

    public static OptionsResponseDto from(Options options){
        return new OptionsResponseDto(
                options.getContent()
        );
    }
}
