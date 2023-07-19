package efub.eday.edayback.domain.member.dto;

import efub.eday.edayback.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
	private String nickname;
	private String profileImageUrl;
	private Integer level;

	public MemberResponseDto(Member member) {
		this.nickname = member.getNickname();
		this.profileImageUrl = member.getProfileImageUrl();
		this.level = member.getLevel();
	}

}
