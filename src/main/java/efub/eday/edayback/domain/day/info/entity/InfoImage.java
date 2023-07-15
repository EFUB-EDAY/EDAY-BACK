package efub.eday.edayback.domain.day.info.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
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

	@Column(name = "image_url")
	private String url;

	@ManyToOne
	@JoinColumn(name = "info_id")
	private Info info;

	@Builder
	public InfoImage(Long id, String url, Info info) {
		this.id = id;
		this.url = url;
		this.info = info;
	}
}
