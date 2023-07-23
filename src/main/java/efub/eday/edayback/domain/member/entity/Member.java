package efub.eday.edayback.domain.member.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "members")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Column(nullable = false, length = 30)
	private String nickname;

	@Column(nullable = false)
	private Long loginId;

	@Column(name = "profile_image_url", nullable = false)
	private String profileImageUrl;

	@Column(name = "grade", nullable = false)
	private Integer level;

	@CreationTimestamp   //insert할 때 자동 날짜 적용
	@Column(nullable = false)
	private LocalDateTime createdDate;

	@Column(nullable = false)
	private Boolean isActive;

	@Builder
	public Member(String nickname, Long loginId, String profileImageUrl) {
		this.nickname = nickname;
		this.loginId = loginId;
		this.profileImageUrl = profileImageUrl;
		this.level = 1;
		this.isActive = true;
	}

	public void updateProfile(String nickname, String profileImageUrl) {
		this.nickname = nickname;
		this.profileImageUrl = profileImageUrl;
	}
}
