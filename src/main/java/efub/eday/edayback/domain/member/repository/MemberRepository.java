package efub.eday.edayback.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import efub.eday.edayback.domain.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByEmail(String email);

	boolean existsByEmail(String email);
}
