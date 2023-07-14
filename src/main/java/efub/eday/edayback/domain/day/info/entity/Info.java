package efub.eday.edayback.domain.day.info.entity;

import java.util.List;

import efub.eday.edayback.domain.day.dday.entity.DDay;
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
	private Long id;

	@OneToOne(mappedBy = "info")
	@JoinColumn(name = "d_day_id")
	private DDay dDay;

	@OneToMany(mappedBy = "info")
	private List<InfoImage> infoImageList;

	@Builder
	public Info(Long id, DDay dDay, List<InfoImage> infoImageList) {
		this.id = id;
		this.dDay = dDay;
		this.infoImageList = infoImageList;
	}
}
