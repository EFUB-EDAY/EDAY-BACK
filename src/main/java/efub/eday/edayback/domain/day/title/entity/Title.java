package efub.eday.edayback.domain.day.title.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import efub.eday.edayback.domain.day.dday.entity.Subject;

@Entity
public class Title {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "title_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(name = "image_url", nullable = false)
	private String imageUrl;

	@OneToOne
	@JoinColumn(nullable = false)
	private Subject subject;
}
