package efub.eday.edayback.domain.day.quiz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
