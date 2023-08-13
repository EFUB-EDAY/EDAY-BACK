package efub.eday.edayback.domain.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/*
{
    "queryContent" : "D-3 내용 문의드립니다!",
	"dday" : 3
}
 */

@Getter
@Setter
public class QueryRequestDto {
	@Schema(description = "문의사항 내용", example = "D-3 내용 문의 드립니다!")
	private String queryContent;
	@Schema(description = "디데이", example = "3")
	private int dday;
}
