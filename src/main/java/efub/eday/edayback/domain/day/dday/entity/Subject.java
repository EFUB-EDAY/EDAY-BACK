package efub.eday.edayback.domain.day.dday.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "subjects")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subject_id")
	private Integer id;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "d_day", nullable = false)
	private Dday dday;

	@Column(nullable = false)
	private String headline;

	public int getDday() {
		return dday.getRemainingDays();
	}

	public String getHeadline() {
		return headline;
	}
}
