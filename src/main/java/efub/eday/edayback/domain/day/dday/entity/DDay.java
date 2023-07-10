package efub.eday.edayback.domain.day.dday.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DDay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "d_day_id")
	private Long id;
}
