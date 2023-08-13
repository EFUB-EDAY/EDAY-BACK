package efub.eday.edayback.domain.day.info.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDto {
	@Schema(description = "이미지 pk")
	private Long imageId;
	@Schema(description = "이미지 url")
	private String imageUrl;
}
