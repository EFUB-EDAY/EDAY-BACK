package efub.eday.edayback.domain.member.auth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import efub.eday.edayback.domain.member.auth.dto.AuthResponseDto;
import efub.eday.edayback.domain.member.auth.dto.KakaoProfileResponseDto;
import efub.eday.edayback.domain.member.auth.dto.KakaoTokenRequestDto;
import efub.eday.edayback.domain.member.auth.dto.KakaoTokenResponseDto;
import efub.eday.edayback.domain.member.auth.service.feign.KakaoApiFeign;
import efub.eday.edayback.domain.member.auth.service.feign.KakaoAuthFeign;
import efub.eday.edayback.domain.member.auth.service.jwt.JwtProvider;
import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

	private static final String GRANT_TYPE_VALUE = "authorization_code";
	private static final String BEARER = "Bearer ";

	private final KakaoAuthFeign kakaoAuthFeign;
	private final KakaoApiFeign kakaoApiFeign;
	private final MemberRepository memberRepository;
	private final JwtProvider jwtProvider;

	@Value("${kakao.client-id}")
	private String clientId;

	@Value("${kakao.redirect-uri}")
	private String redirectUri;

	public AuthResponseDto signIn(String code) {
		String kakaoToken = getKakaoToken(code);
		log.debug("kakaoToken: {}", kakaoToken);
		Member member = getKakaoProfile(kakaoToken);
		log.debug("member login: {} {}", member.getId(), member.getNickname());
		String accessToken = jwtProvider.createAccessToken(member.getId());
		String refreshToken = jwtProvider.createRefreshToken(member.getId());
		AuthResponseDto authResponseDto = AuthResponseDto.builder()
			.member(member)
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
		log.debug("nickname: {} / access: {} / refresh: {}", authResponseDto.getNickname(),
			authResponseDto.getAccessToken(), authResponseDto.getRefreshToken());
		return authResponseDto;
	}

	private String getKakaoToken(String code) {
		KakaoTokenResponseDto tokenResponseDto = kakaoAuthFeign.getKakaoToken(
			KakaoTokenRequestDto.builder()
				.grant_type(GRANT_TYPE_VALUE)
				.client_id(clientId)
				.redirect_uri(redirectUri)
				.code(code)
				.build()
		);
		return tokenResponseDto.getAccess_token();
	}

	private Member getKakaoProfile(String kakaoToken) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.AUTHORIZATION, BEARER + kakaoToken);
		KakaoProfileResponseDto kakaoProfile = kakaoApiFeign.getKakaoProfile(httpHeaders);

		log.debug("카카오 프로필: {}", kakaoProfile.getNickname());

		Long loginId = kakaoProfile.getId();
		String nickname = kakaoProfile.getNickname();
		String profileImageUrl = kakaoProfile.getProfileImageUrl();

		Member member = memberRepository.findByLoginId(loginId).orElse(null);
		if (member != null) {
			member.updateProfile(nickname, profileImageUrl);
			return member;
		}
		member = Member.builder()
			.loginId(loginId)
			.nickname(nickname)
			.profileImageUrl(profileImageUrl)
			.build();
		signUp(member);
		return member;
	}

	private void signUp(Member member) {
		memberRepository.save(member);
	}
}
