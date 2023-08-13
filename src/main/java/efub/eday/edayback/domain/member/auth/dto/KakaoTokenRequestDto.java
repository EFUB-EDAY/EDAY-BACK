package efub.eday.edayback.domain.member.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class KakaoTokenRequestDto {

	@Schema(description = "authoriztion_code로 고정된 타입")
	private String grant_type;
	@Schema(description = "클라이언트 아이디(rest api 키)")
	private String client_id;
	@Schema(description = "리다이렉트 uri")
	private String redirect_uri;
	@Schema(description = "인가 코드")
	private String code;
}
