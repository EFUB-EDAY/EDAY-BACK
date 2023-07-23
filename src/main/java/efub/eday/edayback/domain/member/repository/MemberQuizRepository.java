package efub.eday.edayback.domain.member.repository;

import efub.eday.edayback.domain.day.quiz.entity.Quiz;
import efub.eday.edayback.domain.member.entity.Member;
import efub.eday.edayback.domain.member.entity.MemberQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberQuizRepository extends JpaRepository <MemberQuiz, Long> {
    Optional<MemberQuiz> findByMemberAndQuiz(Member member, Quiz quiz);
}
