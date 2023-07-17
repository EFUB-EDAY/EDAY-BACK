package efub.eday.edayback.domain.member.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.member.entity.oauth.KakaoProfile;
import efub.eday.edayback.domain.member.entity.oauth.OAuthToken;
import efub.eday.edayback.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public Member getAccessToken(String code) {

		RestTemplate rt = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "24964b4a10d417e6a957b25423a635a5");
		params.add("redirect_uri", "http://localhost:8080/members/oauth");
		params.add("code", code);

		//아래 exchange 함수에 하나의 object로 넣기 위해 합치기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		ResponseEntity<String> response = rt.exchange(
			"https://kauth.kakao.com/oauth/token",
			HttpMethod.POST,
			kakaoTokenRequest,
			String.class
		);

		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return findProfile(oauthToken);

	}

	private Member findProfile(OAuthToken oauthToken) {
		//토큰 이용하여 사용자 정보 조회
		RestTemplate rt2 = new RestTemplate();

		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

		ResponseEntity<String> response2 = rt2.exchange(
			"https://kapi.kakao.com/v2/user/me",
			HttpMethod.POST,
			kakaoProfileRequest2,
			String.class
		);

		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return saveMember(kakaoProfile);
	}

	public Member saveMember(@RequestBody KakaoProfile kakaoProfile) {
		//Member 저장
		Member kakaoMember = memberRepository.findByEmail(kakaoProfile.getKakao_account().getEmail());

		if (kakaoMember == null) {
			//기존 회원이 아닐경우 자동으로 회원가입
			kakaoMember = Member.builder()
				.nickname(kakaoProfile.getKakao_account().profile.nickname)
				.email(kakaoProfile.getKakao_account().getEmail())
				.profileImageUrl(kakaoProfile.getKakao_account().profile.profile_image_url)
				.level(0) //level은 0에서부터 시작
				.isActive(true) //처음 회원가입하면 활성 상태로 시작
				.build();
			memberRepository.save(kakaoMember);
		}
		return kakaoMember;
	}

}
