package efub.eday.edayback.domain.member.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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
	private String email;

	@Column(name = "profile_image_url", nullable = false)
	private String profileImageUrl;

	@Column(name = "grade", nullable = false)
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
