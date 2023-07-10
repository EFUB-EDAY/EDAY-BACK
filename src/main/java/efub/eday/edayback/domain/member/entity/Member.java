package efub.eday.edayback.domain.member.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

	@Column(nullable = false)
	private LocalDate createdDate;

	@Column(nullable = false)
	private Boolean isActive;
}
