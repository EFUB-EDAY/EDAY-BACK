package efub.eday.edayback.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuizDto {
	private int dday;

	public QuizDto(int dday) {
		this.dday = dday;
	}
}
