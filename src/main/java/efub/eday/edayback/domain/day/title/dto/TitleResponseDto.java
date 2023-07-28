package efub.eday.edayback.domain.day.title.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TitleResponseDto {
	@Schema(description = "사용자 프로필 정보")
	private MemberProfileDto profile;
	@Schema(description = "칭호 목록")
	private List<TitleDto> titleList;

	public TitleResponseDto(MemberProfileDto profile, List<TitleDto> titleList) {
		this.profile = profile;
		this.titleList = titleList;
	}
}
