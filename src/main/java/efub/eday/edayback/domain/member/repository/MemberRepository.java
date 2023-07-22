package efub.eday.edayback.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import efub.eday.edayback.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	Member findByEmail(String email);
}
