package efub.eday.edayback.domain.query.entity;

import efub.eday.edayback.domain.day.dday.entity.Subject;
import efub.eday.edayback.domain.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "querys")
public class Query {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "query_id")
	private Integer id;

	@Column(nullable = false)
	private String content;

	@ManyToOne
	@JoinColumn(name = "member_id", nullable = false)
	private Member writer;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Subject subject;

	@Builder
	public Query(String content, Member writer, Subject subject) {
		this.content = content;
		this.writer = writer;
		this.subject = subject;
	}
}
