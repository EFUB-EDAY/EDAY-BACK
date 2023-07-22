package efub.eday.edayback.domain.day.quiz.dto;

import efub.eday.edayback.domain.day.quiz.entity.Option;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OptionsResponseDto {
	private String content;

	private OptionsResponseDto(String content) {
		this.content = content;
	}

	public static OptionsResponseDto from(Option option) {
		return new OptionsResponseDto(
			option.getContent()
		);
	}
}
