package efub.eday.edayback.global.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import efub.eday.edayback.domain.member.auth.dto.KakaoTokenRequestDto;
import efub.eday.edayback.domain.member.auth.dto.KakaoTokenResponseDto;

@FeignClient(name = "KakaoAuthFeign", url = "https://kauth.kakao.com/oauth/token")
public interface KakaoAuthFeign {

	@PostMapping(consumes = "application/x-www-form-urlencoded")
	KakaoTokenResponseDto getKakaoToken(KakaoTokenRequestDto requestDto);
}
