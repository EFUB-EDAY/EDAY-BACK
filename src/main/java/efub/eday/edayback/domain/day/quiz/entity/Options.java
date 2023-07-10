package efub.eday.edayback.domain.day.quiz.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Options {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "option_id")
	private Long id;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private Boolean isAnswer;

	@ManyToOne
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;
}
