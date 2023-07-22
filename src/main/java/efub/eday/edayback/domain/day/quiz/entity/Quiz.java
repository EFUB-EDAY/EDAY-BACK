package efub.eday.edayback.domain.day.quiz.entity;

import java.util.List;

import efub.eday.edayback.domain.day.dday.entity.Subject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quiz_id")
	private Long id;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private String explanation;

	@Column(name = "image_url")
	private String imageUrl;

	@OneToOne
	@JoinColumn(name = "subject_id", nullable = false)
	private Subject subject;

	@OneToMany(mappedBy = "quiz")
	private List<Options> optionsList;

	@Builder
	public Quiz(String content, String explanation, String imageUrl, Subject subject, List<Options> optionsList) {
		this.content = content;
		this.explanation = explanation;
		this.imageUrl = imageUrl;
		this.subject = subject;
		this.optionsList = optionsList;
	}

}
