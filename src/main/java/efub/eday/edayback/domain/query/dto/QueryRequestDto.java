package efub.eday.edayback.domain.query.dto;

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
	private String queryContent;
	private int dday;
}
