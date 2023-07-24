package efub.eday.edayback.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDto {
	private String nickname;
	private String profileImageUrl;
	private int level;
	private int dDay;
	private String titleName;

	@Builder
	public ProfileDto(String nickname, String profileImageUrl, int level, String titleName) {
		this.nickname = nickname;
		this.profileImageUrl = profileImageUrl;
		this.level = level;
		this.dDay = 7 - level;
		this.titleName = titleName;
	}
}
