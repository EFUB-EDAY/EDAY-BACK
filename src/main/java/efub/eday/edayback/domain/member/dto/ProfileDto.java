package efub.eday.edayback.domain.member.dto;

import efub.eday.edayback.domain.member.entity.Member;
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
	public ProfileDto(Member member) {
		this.nickname = member.getNickname();
		this.profileImageUrl = member.getProfileImageUrl();
		this.level = member.getLevel();
		this.dDay = 7 - member.getLevel();
	}
}
