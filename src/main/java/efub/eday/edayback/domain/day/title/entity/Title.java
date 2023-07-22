package efub.eday.edayback.domain.day.title.entity;

import efub.eday.edayback.domain.day.dday.entity.Subject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "titles")
public class Title {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "title_id")
	private Integer id;

	@Column(name = "title_name", nullable = false, length = 127)
	private String name;

	@Column(name = "image_url", nullable = false)
	private String imageUrl;

	@OneToOne
	@JoinColumn(name = "subject_id", nullable = false)
	private Subject subject;

	@Builder
	public Title(String name, String imageUrl, Subject subject) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.subject = subject;
	}
}
