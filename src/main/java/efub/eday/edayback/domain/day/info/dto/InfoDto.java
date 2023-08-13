package efub.eday.edayback.domain.day.info.dto;

import java.util.ArrayList;
import java.util.List;

import efub.eday.edayback.domain.day.info.entity.Info;
import efub.eday.edayback.domain.day.info.entity.InfoImage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InfoDto {
	// private Long infoId;
	// private Long infoImageId;
	@Schema(description = "디데이 일수")
	private int dday;
	@Schema(description = "추가정보 주제")
	private String headline;
	@Schema(description = "사진 url 리스트")
	private List<ImageDto> imageList;

	public static InfoDto from(Info info) {
		InfoDto infoDto = new InfoDto();

		infoDto.setDday(info.getSubject().getDday());
		infoDto.setHeadline(info.getSubject().getHeadline());

		List<ImageDto> imageList = new ArrayList<>();
		for (InfoImage infoImage : info.getInfoImageList()) {
			ImageDto imageDto = new ImageDto();
			imageDto.setImageId(infoImage.getId());
			imageDto.setImageUrl(infoImage.getUrl());
			imageList.add(imageDto);
		}
		infoDto.setImageList(imageList);

		return infoDto;
	}
}

