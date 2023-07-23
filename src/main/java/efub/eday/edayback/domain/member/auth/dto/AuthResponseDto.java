package efub.eday.edayback.domain.member.auth.dto;

import efub.eday.edayback.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthResponseDto {

	private Long memberId;
	private String nickname;
	private String profileImage;
	private Integer level;
	private String accessToken;
	private String refreshToken;

	@Builder
	public AuthResponseDto(Member member, String accessToken, String refreshToken) {
		this.memberId = member.getId();
		this.nickname = member.getNickname();
		this.profileImage = member.getProfileImageUrl();
		this.level = member.getLevel();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
}
