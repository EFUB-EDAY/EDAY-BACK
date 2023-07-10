package efub.eday.edayback.domain.day.info.entity;

import efub.eday.edayback.domain.day.dday.entity.DDay;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Info {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "info_id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "d_day_id")
	private DDay dDay;
}
