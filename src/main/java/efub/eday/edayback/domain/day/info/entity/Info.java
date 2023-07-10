package efub.eday.edayback.domain.day.info.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Info {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "info_id")
	private Long id;
}
