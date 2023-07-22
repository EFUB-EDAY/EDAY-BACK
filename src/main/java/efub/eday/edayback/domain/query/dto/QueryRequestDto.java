package efub.eday.edayback.domain.query.dto;

import lombok.Getter;

/*
{
	"memberId" : 1,
    "queryContent" : "공강을 보내기 좋은 장소에 관련하여 문의 드립니다.",
	"subjectId" : 1
}
 */

@Getter
public class QueryRequestDto {
	private int memberId;
	private String queryContent;
	private int subjectId;
}
