package efub.eday.edayback.domain.day.title.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberProfileDto {

	@Schema(description = "사용자 닉네임")
	private String nickname;
	@Schema(description = "프로필 이미지 URL")
	private String profileImage;
	@Schema(description = "레벨")
	private int level;
	@Schema(description = "계정 생성일")
	private LocalDateTime createdDate;
	@Schema(description = "활성 상태 여부")
	private boolean isActive;

	public MemberProfileDto(String nickname, String profileImage, int level, LocalDateTime createdDate,
		boolean isActive) {
		this.nickname = nickname;
		this.profileImage = profileImage;
		this.level = level;
		this.createdDate = createdDate;
		this.isActive = isActive;
	}
}
