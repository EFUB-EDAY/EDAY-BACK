package efub.eday.edayback.domain.day.quiz.dto;

import efub.eday.edayback.domain.day.quiz.entity.Option;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OptionsResponseDto {
	private Integer optionNumber;
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
