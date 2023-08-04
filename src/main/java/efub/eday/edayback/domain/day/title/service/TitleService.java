package efub.eday.edayback.domain.day.title.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import efub.eday.edayback.domain.day.title.dto.MemberProfileDto;
import efub.eday.edayback.domain.day.title.dto.TitleDto;
import efub.eday.edayback.domain.day.title.dto.TitleResponseDto;
import efub.eday.edayback.domain.day.title.entity.Title;
import efub.eday.edayback.domain.member.auth.service.AuthService;
import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.member.entity.MemberTitle;
import efub.eday.edayback.domain.member.repository.MemberTitleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TitleService {

	private final MemberTitleRepository memberTitleRepository;
	private final AuthService authService;

	@Transactional(readOnly = true)
	public TitleResponseDto getTitlePage() {
		Member member = authService.getCurrentMember();

		List<MemberTitle> memberTitles = memberTitleRepository.findByMember(member);

		MemberProfileDto profile = new MemberProfileDto(
			member.getNickname(),
			member.getProfileImageUrl(),
			member.getLevel(),
			member.getCreatedDate(),
			member.getIsActive()
		);

		List<TitleDto> titleList = memberTitles.stream()
			.map(memberTitle -> {
				Title title = memberTitle.getTitle();
				return new TitleDto(
					title.getSubject().getDday(),
					title.getName(),
					title.getImageWithNotTextUrl(),
					memberTitle.getGetTitle()
				);
			})
			.collect(Collectors.toList());

		return new TitleResponseDto(profile, titleList);
	}

	@Transactional(readOnly = true)
	public TitleDto getTitleByDday(int dday) {
		Member member = authService.getCurrentMember();
		MemberTitle memberTitle = memberTitleRepository.findByMemberAndTitleSubjectDday(member, dday)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 디데이의 칭호를 찾을 수 없습니다."));

		Title title = memberTitle.getTitle();
		return new TitleDto(
			dday,
			title.getName(),
			title.getImageUrl(),
			memberTitle.getGetTitle()
		);
	}
}
