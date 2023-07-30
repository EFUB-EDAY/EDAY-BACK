package efub.eday.edayback.domain.member.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizListDto {
	@Schema(description = "풀 수 있도록 열려있는 퀴즈 리스트")
	private List<QuizDto> openList;
	@Schema(description = "회원이 푼 퀴즈 리스트")
	private List<QuizDto> doneList;
	@Schema(description = "아직 날짜가 되지 않아 풀 수 없는 퀴즈 리스트")
	private List<QuizDto> closeList;

}
