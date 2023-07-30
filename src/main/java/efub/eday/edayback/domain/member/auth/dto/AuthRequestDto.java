package efub.eday.edayback.domain.member.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthRequestDto {
	@Schema(description = "인가 코드")
	private String code;
}
