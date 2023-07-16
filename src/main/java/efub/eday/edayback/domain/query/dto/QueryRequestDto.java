package efub.eday.edayback.domain.query.dto;

import lombok.Getter;

/*
{
    "queryContent" : "공강을 보내기 좋은 장소에 관련하여 문의 드립니다.",
	"dDay" : 3
}
 */

@Getter
public class QueryRequestDto {
	private Long memberId;
	private String queryContent;
	private int dDay;
}
