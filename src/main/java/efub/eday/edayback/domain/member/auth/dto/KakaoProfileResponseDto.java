package efub.eday.edayback.domain.member.auth.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class KakaoProfileResponseDto {

	private Long id;
	private KakaoAccount kakao_account;

	@Setter
	@NoArgsConstructor
	public class KakaoAccount {

		private Profile profile;

		@Setter
		@NoArgsConstructor
		public class Profile {
			private String nickname;
			private String profile_image_url;
			private Boolean is_default_image;
		}
	}

	public Long getId() {
		return id;
	}

	public String getNickname() {
		return kakao_account.profile.nickname;
	}

	public String getProfileImageUrl() {
		return kakao_account.profile.profile_image_url;
	}
}
