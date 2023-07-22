package efub.eday.edayback.domain.day.quiz.dto;

import java.util.List;
import java.util.stream.Collectors;

import efub.eday.edayback.domain.day.quiz.entity.Quiz;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizResponseDto {
	private int dday;
	private String topic;
	private String quizContent;
	private List<OptionsResponseDto> optionList;

	public QuizResponseDto(int dday, String topic, String quizContent, List<OptionsResponseDto> optionList) {
		this.dday = dday;
		this.topic = topic;
		this.quizContent = quizContent;
		this.optionList = optionList;
	}

	public static QuizResponseDto from(Quiz quiz) {
		List<OptionsResponseDto> optionList = quiz.getOptionList()
			.stream()
			.map(OptionsResponseDto::from)
			.collect(Collectors.toList());
		return new QuizResponseDto(
			quiz.getSubject().getDday(),
			quiz.getSubject().getHeadline(),
			quiz.getContent(),
			optionList
		);
	}

}
