package efub.eday.edayback.domain.day.quiz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import efub.eday.edayback.domain.day.dday.entity.Dday;
import efub.eday.edayback.domain.day.quiz.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
	Optional<Quiz> findBySubject_Dday(Dday dday);
}
