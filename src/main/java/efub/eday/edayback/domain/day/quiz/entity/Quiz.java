package efub.eday.edayback.domain.day.quiz.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import efub.eday.edayback.domain.day.dday.entity.Subject;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
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
	@JoinColumn(name = "d_day_id", nullable = false)
	private Subject subject;

	@OneToMany(mappedBy = "quiz")
	private List<Options> optionsList;

	@Builder
	public Quiz(String content, String description, String imageUrl, Subject subject, List<Options> optionsList) {
		this.content = content;
		this.description = description;
		this.imageUrl = imageUrl;
		this.subject = subject;
		this.optionsList = optionsList;
	}
}
