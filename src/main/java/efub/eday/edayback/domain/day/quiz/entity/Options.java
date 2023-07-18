package efub.eday.edayback.domain.day.quiz.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
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
	public Options(String content, Boolean isAnswer, Quiz quiz){
		this.content=content;
		this.isAnswer=isAnswer;
		this.quiz=quiz;
	}
}
