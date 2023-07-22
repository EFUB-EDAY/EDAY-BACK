package efub.eday.edayback.domain.day.quiz.entity;

import efub.eday.edayback.domain.day.dday.entity.Subject;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quiz_id")
	private Integer id;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false, length = 500)
	private String explanation;

	@Column(name = "image_url")
	private String imageUrl;

	@OneToOne
	@JoinColumn(name = "d_day_id", nullable = false)
	private Subject subject;

	@OneToMany(mappedBy = "quiz")
	private List<Option> optionList;

	@Builder
	public Quiz(String content, String explanation, String imageUrl, Subject subject, List<Option> optionList) {
		this.content = content;
		this.explanation = explanation;
		this.imageUrl = imageUrl;
		this.subject = subject;
		this.optionList = optionList;
	}
}
