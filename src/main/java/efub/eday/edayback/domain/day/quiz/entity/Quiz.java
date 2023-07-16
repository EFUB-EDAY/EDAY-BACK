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
	private Long id;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private String description;

	@Column(name = "image_url")
	private String imageUrl;

	@OneToOne
	@JoinColumn(name = "d_day_id", nullable = false)
	private Subject dday;

	@OneToMany(mappedBy = "quiz")
	private List<Options> optionsList;

	@Builder
	public Quiz(Long id, String content, String description, String imageUrl, Subject dday, List<Options> optionsList){
		this.id=id;
		this.content=content;
		this.description=description;
		this.imageUrl=imageUrl;
		this.dday=dday;
		this.optionsList=optionsList;
	}
}
