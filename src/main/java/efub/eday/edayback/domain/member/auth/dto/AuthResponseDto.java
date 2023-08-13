package efub.eday.edayback.domain.member.auth.dto;

import efub.eday.edayback.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthResponseDto {

	@Schema(description = "회원 아이디 pk")
	private Long memberId;
	@Schema(description = "닉네임")
	private String nickname;
	@Schema(description = "프로필 사진 url")
	private String profileImage;
	@Schema(description = "레벨")
	private Integer level;
	@Schema(description = "accessToken")
	private String accessToken;
	@Schema(description = "refreshToken")
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
