package efub.eday.edayback.domain.day.quiz.entity;

import efub.eday.edayback.domain.day.dday.entity.Subject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quiz_id")
	private Long id;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private String description;

	@Column(name = "image_url")
	private String imageUrl;

	@OneToOne
	@JoinColumn(nullable = false)
	private Subject subject;
}
