package efub.eday.edayback.domain.member.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KakaoTokenResponseDto {
	@Schema(description = "토큰 타입")
	private String token_type;
	@Schema(description = "accessToken")
	private String access_token;
	@Schema(description = "accessToken 만료 기한")
	private int expires_in;
	@Schema(description = "refreshToken")
	private String refresh_token;
	@Schema(description = "refreshToken 만료 기한")
	private int refresh_token_expires_in;
	@Schema(description = "인가 코드")
	private String scope;
}
