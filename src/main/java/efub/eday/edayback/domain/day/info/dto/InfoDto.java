package efub.eday.edayback.domain.day.info.dto;

import java.util.ArrayList;
import java.util.List;

import efub.eday.edayback.domain.day.info.entity.Info;
import efub.eday.edayback.domain.day.info.entity.InfoImage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InfoDto {
	private Long infoId;
	private Long infoImageId;
	private int dDay;
	private List<ImageDto> imageList;

	public static InfoDto from(Info info) {
		InfoDto infoDto = new InfoDto();
		infoDto.setInfoId(info.getId());
		infoDto.setDDay(info.getDDay().getDDay());

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

