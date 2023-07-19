package efub.eday.edayback.domain.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import efub.eday.edayback.domain.day.title.entity.Title;

@Entity
public class MemberTitle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_title_id")
	private Long id;

	@Column(nullable = false)
	private Boolean getTitle;

	@ManyToOne
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@ManyToOne
	@JoinColumn(name = "title_id", nullable = false)
	private Title title;
}
