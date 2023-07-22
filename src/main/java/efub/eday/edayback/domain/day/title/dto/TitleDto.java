package efub.eday.edayback.domain.day.title.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TitleDto {

	private int dday;
	private String titleName;
	private String titleImageUrl;
	private boolean getTitle;

	public TitleDto(int dday, String titleName, String titleImageUrl, boolean getTitle) {
		this.dday = dday;
		this.titleName = titleName;
		this.titleImageUrl = titleImageUrl;
		this.getTitle = getTitle;
	}
}
