package efub.eday.edayback.domain.day.quiz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
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

	@Builder
	public Options(String content, Boolean isAnswer, Quiz quiz) {
		this.content = content;
		this.isAnswer = isAnswer;
		this.quiz = quiz;
	}
}
