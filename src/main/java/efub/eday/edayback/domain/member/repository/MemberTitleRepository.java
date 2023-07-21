package efub.eday.edayback.domain.member.repository;

import efub.eday.edayback.domain.day.title.entity.Title;
import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.member.entity.MemberTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberTitleRepository extends JpaRepository<MemberTitle, Long> {
    Optional<MemberTitle> findByMemberAndTitle(Member member, Title title);
}
