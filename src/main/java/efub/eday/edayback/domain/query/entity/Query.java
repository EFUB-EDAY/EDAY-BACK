package efub.eday.edayback.domain.query.entity;

import efub.eday.edayback.domain.day.dday.entity.DDay;
import efub.eday.edayback.domain.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Query {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "query_id")
	private Long id;

	@Column(nullable = false)
	private String content;

	@ManyToOne
	@JoinColumn(name = "member_id", nullable = false)
	private Member writer;

	@ManyToOne
	@JoinColumn(name = "d_day_id", nullable = false)
	private DDay dDay;
}
