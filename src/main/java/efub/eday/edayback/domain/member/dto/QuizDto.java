package efub.eday.edayback.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuizDto {
	@Schema(description = "디데이 일수")
	private int dday;

	public QuizDto(int dday) {
		this.dday = dday;
	}
}
