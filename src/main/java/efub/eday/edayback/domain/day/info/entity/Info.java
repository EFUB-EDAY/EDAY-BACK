package efub.eday.edayback.domain.day.info.entity;

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
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Info {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "info_id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "subject_id", nullable = false)
	private Subject subject;

	@OneToMany(mappedBy = "info")
	private List<InfoImage> infoImageList;

	@Builder
	public Info(Subject subject, List<InfoImage> infoImageList) {
		this.subject = subject;
		this.infoImageList = infoImageList;
	}
}
