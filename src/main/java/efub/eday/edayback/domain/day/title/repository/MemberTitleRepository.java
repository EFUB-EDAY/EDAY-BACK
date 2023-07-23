package efub.eday.edayback.domain.day.title.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import efub.eday.edayback.domain.day.title.entity.Title;
import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.member.entity.MemberTitle;

@Repository
public interface MemberTitleRepository extends JpaRepository<MemberTitle, Integer> {
	List<MemberTitle> findByMember(Member member);

	Optional<MemberTitle> findByMemberAndTitle(Member member, Title title);
}
