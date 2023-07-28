package efub.eday.edayback.domain.member.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizListDto {
	private List<QuizDto> openList;
	private List<QuizDto> doneList;
	private List<QuizDto> closeList;

}
