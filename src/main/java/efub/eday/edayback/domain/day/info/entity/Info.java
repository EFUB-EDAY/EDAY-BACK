package efub.eday.edayback.domain.day.info.entity;

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
@NoArgsConstructor
@Getter
public class Info {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "info_id")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "subject_id", nullable = false, unique = true)
	private Subject subject;

	@OneToMany(mappedBy = "info")
	private List<InfoImage> infoImageList;

	@Builder
	public Info(Subject subject, List<InfoImage> infoImageList) {
		this.subject = subject;
		this.infoImageList = infoImageList;
	}
}
