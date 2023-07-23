package efub.eday.edayback.domain.day.title.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.member.entity.MemberTitle;

@Repository
public interface MemberTitleRepository extends JpaRepository<MemberTitle, Long> {
	List<MemberTitle> findByMember(Member member);
}