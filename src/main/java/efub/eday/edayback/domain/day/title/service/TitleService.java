package efub.eday.edayback.domain.day.title.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import efub.eday.edayback.domain.day.title.dto.MemberProfileDto;
import efub.eday.edayback.domain.day.title.dto.TitleDto;
import efub.eday.edayback.domain.day.title.dto.TitleResponseDto;
import efub.eday.edayback.domain.day.title.entity.Title;
import efub.eday.edayback.domain.day.title.repository.MemberTitleRepository;
import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.member.entity.MemberTitle;
import efub.eday.edayback.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TitleService {

	private final MemberRepository memberRepository;
	private final MemberTitleRepository memberTitleRepository;

	@Transactional(readOnly = true)
	public TitleResponseDto getTitlePage(Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 없습니다. ID: " + memberId));

		List<MemberTitle> memberTitles = memberTitleRepository.findByMember(member);

		MemberProfileDto profile = new MemberProfileDto(
			member.getNickname(),
			member.getEmail(),
			member.getProfileImageUrl(),
			member.getLevel(),
			member.getCreatedDate().atStartOfDay(),
			member.getIsActive()
		);

		List<TitleDto> titleList = memberTitles.stream()
			.map(memberTitle -> {
				Title title = memberTitle.getTitle();
				return new TitleDto(
					title.getSubject().getDday(),
					title.getName(),
					title.getImageUrl(),
					memberTitle.getGetTitle()
				);
			})
			.collect(Collectors.toList());

		return new TitleResponseDto(profile, titleList);
	}
}
