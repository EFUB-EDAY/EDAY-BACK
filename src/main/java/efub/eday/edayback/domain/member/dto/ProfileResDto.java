package efub.eday.edayback.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileResDto {
	@Schema(description = "회원정보")
	private ProfileDto profile;
	@Schema(description = "풀 수 있는, 푼, 아직 풀 수 없는 퀴즈 리스트")
	private QuizListDto quiz;

	public ProfileResDto(ProfileDto profile, QuizListDto quiz) {
		this.profile = profile;
		this.quiz = quiz;
	}

}

