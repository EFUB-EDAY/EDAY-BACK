package efub.eday.edayback.domain.member.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import efub.eday.edayback.domain.day.quiz.entity.Quiz;
import efub.eday.edayback.domain.day.quiz.repository.QuizRepository;
import efub.eday.edayback.domain.day.title.entity.Title;
import efub.eday.edayback.domain.day.title.repository.TitleRepository;
import efub.eday.edayback.domain.member.auth.dto.AuthResponseDto;
import efub.eday.edayback.domain.member.auth.dto.KakaoProfileResponseDto;
import efub.eday.edayback.domain.member.auth.dto.KakaoTokenRequestDto;
import efub.eday.edayback.domain.member.auth.dto.KakaoTokenResponseDto;
import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.member.entity.MemberQuiz;
import efub.eday.edayback.domain.member.entity.MemberTitle;
import efub.eday.edayback.domain.member.repository.MemberQuizRepository;
import efub.eday.edayback.domain.member.repository.MemberRepository;
import efub.eday.edayback.domain.member.repository.MemberTitleRepository;
import efub.eday.edayback.global.feign.KakaoApiFeign;
import efub.eday.edayback.global.feign.KakaoAuthFeign;
import efub.eday.edayback.global.jwt.JwtProvider;
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
	private final QuizRepository quizRepository;
	private final MemberQuizRepository memberQuizRepository;
	private final TitleRepository titleRepository;
	private final MemberTitleRepository memberTitleRepository;
	private final JwtProvider jwtProvider;

	@Value("${kakao.client-id}")
	private String clientId;

	@Value("${kakao.redirect-uri}")
	private String redirectUri;

	public AuthResponseDto signIn(String code) {
		try {
			Member currentMember = getCurrentMember();
			return createAuthResponse(currentMember);
		} catch (ResponseStatusException err) {
			String kakaoToken = getKakaoToken(code);
			log.debug("kakaoToken: {}", kakaoToken);
			Member member = getKakaoProfile(kakaoToken);
			log.debug("member login: {} {}", member.getId(), member.getNickname());
			return createAuthResponse(member);
		}
	}

	private AuthResponseDto createAuthResponse(Member member) {
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

		List<Quiz> quizs = quizRepository.findAll();
		for (Quiz quiz : quizs) {
			MemberQuiz mQuiz = new MemberQuiz(member, quiz);
			memberQuizRepository.save(mQuiz);
		}

		List<Title> titles = titleRepository.findAll();
		for (Title title : titles) {
			MemberTitle mTitle = new MemberTitle(member, title);
			memberTitleRepository.save(mTitle);
		}

	}

	public Member getCurrentMember() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String principalName = authentication.getName();
		Long memberId = Long.parseLong(principalName);
		return memberRepository.findById(memberId).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.FORBIDDEN, "인증된 사용자 정보가 없습니다."));
	}
}
