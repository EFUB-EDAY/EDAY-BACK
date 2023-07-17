package efub.eday.edayback.domain.member.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Column(nullable = false)
	private String nickname;

	@Column(nullable = false)
	private String email;

	@Column(name = "profile_image_url", nullable = false)
	private String profileImageUrl;

	@Column(nullable = false)
	private Integer level;

	@CreationTimestamp   //insert할 때 자동 날짜 적용
	@Column(nullable = false)
	private LocalDate createdDate;

	@Column(nullable = false)
	private Boolean isActive;

	@Builder
	public Member(String nickname, String email, String profileImageUrl, Integer level, Boolean isActive) {
		this.nickname = nickname;
		this.email = email;
		this.profileImageUrl = profileImageUrl;
		this.level = level;
		this.isActive = isActive;
	}
}
