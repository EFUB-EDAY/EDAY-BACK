package efub.eday.edayback.domain.member.dto;

import efub.eday.edayback.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponseDto {
	private Long memberId;
	private String email;
	private String nickname;
	private String accessToken;

	@Builder
	public LoginResponseDto(Member member, String accessToken) {
		this.memberId = member.getId();
		this.email = member.getEmail();
		this.nickname = member.getNickname();
		this.accessToken = accessToken;
	}
}
