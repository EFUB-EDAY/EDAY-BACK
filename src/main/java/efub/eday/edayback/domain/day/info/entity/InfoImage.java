package efub.eday.edayback.domain.day.info.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class InfoImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "info_image_id")
	private Long id;

	@Column(name = "image_url", nullable = false)
	private String url;

	@ManyToOne
	@JoinColumn(name = "info_id", nullable = false)
	private Info info;
}
