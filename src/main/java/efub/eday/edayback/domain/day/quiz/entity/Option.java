package efub.eday.edayback.domain.day.quiz.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "quiz_option")
public class Option {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "option_id")
	private Integer id;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private Boolean isAnswer;

	@Column(nullable = false)
	private Integer optionNumber;

	@ManyToOne
	@JoinColumn(name = "quiz_id", nullable = false)
	private Quiz quiz;

	@Builder
	public Option(String content, Boolean isAnswer, Quiz quiz, int optionNumber) {
		this.content = content;
		this.isAnswer = isAnswer;
		this.quiz = quiz;
		this.optionNumber = optionNumber;
	}
}
