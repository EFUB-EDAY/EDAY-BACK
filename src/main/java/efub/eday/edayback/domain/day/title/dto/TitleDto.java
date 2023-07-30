package efub.eday.edayback.domain.day.title.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TitleDto {
	@Schema(description = "디데이")
	private int dday;
	@Schema(description = "칭호명")
	private String titleName;
	@Schema(description = "칭호 이미지 URL")
	private String titleImageUrl;
	@Schema(description = "칭호 획득 여부")
	private boolean getTitle;

	public TitleDto(int dday, String titleName, String titleImageUrl, boolean getTitle) {
		this.dday = dday;
		this.titleName = titleName;
		this.titleImageUrl = titleImageUrl;
		this.getTitle = getTitle;
	}
}
