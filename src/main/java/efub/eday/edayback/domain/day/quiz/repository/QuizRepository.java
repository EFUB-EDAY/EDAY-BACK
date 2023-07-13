package efub.eday.edayback.domain.day.quiz.repository;

import efub.eday.edayback.domain.day.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
    Optional<Quiz> findByDDayId(Long dDayId);
}
