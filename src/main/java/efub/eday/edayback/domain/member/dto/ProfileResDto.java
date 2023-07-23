package efub.eday.edayback.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileResDto {
	private ProfileDto profile;
	private QuizListDto quiz;

	public ProfileResDto(ProfileDto profile, QuizListDto quiz) {
		this.profile = profile;
		this.quiz = quiz;
	}
}

