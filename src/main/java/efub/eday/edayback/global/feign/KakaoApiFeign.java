package efub.eday.edayback.global.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import efub.eday.edayback.domain.member.auth.dto.KakaoProfileResponseDto;

@FeignClient(name = "KakaoApiFeign", url = "https://kapi.kakao.com/v2/user/me")
public interface KakaoApiFeign {

	@GetMapping
	KakaoProfileResponseDto getKakaoProfile(@RequestHeader HttpHeaders headers);
}
