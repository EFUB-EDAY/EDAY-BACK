package efub.eday.edayback.domain.query.dto;

import lombok.Getter;

/*
{
	"memberId" : 1,
    "queryContent" : "D-3 내용 문의드립니다!",
	"dday" : 3
}
 */

@Getter
public class QueryRequestDto {
	private int memberId;
	private String queryContent;
	private int dday;
}
