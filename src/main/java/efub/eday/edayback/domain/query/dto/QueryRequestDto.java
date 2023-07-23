package efub.eday.edayback.domain.query.dto;

import lombok.Getter;
import lombok.Setter;

/*
{
	"memberId" : 1,
    "queryContent" : "D-3 내용 문의드립니다!",
	"dday" : 3
}
 */

@Getter
@Setter
public class QueryRequestDto {
	private Long memberId;
	private String queryContent;
	private int dday;
}
