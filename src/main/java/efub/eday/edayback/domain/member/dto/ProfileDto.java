package efub.eday.edayback.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDto {
	@Schema(description = "닉네임")
	private String nickname;
	@Schema(description = "프로필 사진 url")
	private String profileImageUrl;
	@Schema(description = "레벨")
	private int level;
	@Schema(description = "디데이 일수")
	private int dDay;
	@Schema(description = "칭호")
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
