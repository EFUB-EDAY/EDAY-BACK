package efub.eday.edayback.domain.day.quiz.dto;

import efub.eday.edayback.domain.day.quiz.entity.Options;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OptionsResponseDto {
    private Long id;
    private String content;

    private OptionsResponseDto(Long id, String content){
        this.id=id;
        this.content=content;
    }

    public static OptionsResponseDto from(Options options){
        return new OptionsResponseDto(
                options.getId(),
                options.getContent()
        );
    }
}
