package efub.eday.edayback.domain.day.title.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberProfileDto {

	private String nickname;
	private String email;
	private String profileImage;
	private int level;
	private LocalDateTime createdDate;
	private boolean isActive;

	public MemberProfileDto(String nickname, String email, String profileImage, int level, LocalDateTime createdDate,
		boolean isActive) {
		this.nickname = nickname;
		this.email = email;
		this.profileImage = profileImage;
		this.level = level;
		this.createdDate = createdDate;
		this.isActive = isActive;
	}
}
