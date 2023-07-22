package efub.eday.edayback.domain.day.dday.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "subjects")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subject_id")
	private Integer id;

	@Enumerated(value = EnumType.ORDINAL)
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

	@Builder
	public Subject(Dday dday, String headline) {
		this.dday = dday;
		this.headline = headline;
	}
}
