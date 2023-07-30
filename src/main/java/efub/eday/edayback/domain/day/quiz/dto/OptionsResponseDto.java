package efub.eday.edayback.domain.day.quiz.dto;

import efub.eday.edayback.domain.day.quiz.entity.Option;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OptionsResponseDto {
	@Schema(description = "선지 번호")
	private Integer optionNumber;
	@Schema(description = "선지 내용")
	private String content;

	private OptionsResponseDto(Integer optionNumber, String content) {
		this.optionNumber = optionNumber;
		this.content = content;
	}

	public static OptionsResponseDto from(Option option) {
		return new OptionsResponseDto(
				option.getOptionNumber(),
				option.getContent()
		);
	}
}
