package efub.eday.edayback.domain.day.title.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TitleResponseDto {

	private MemberProfileDto profile;
	private List<TitleDto> titleList;

	public TitleResponseDto(MemberProfileDto profile, List<TitleDto> titleList) {
		this.profile = profile;
		this.titleList = titleList;
	}
}
