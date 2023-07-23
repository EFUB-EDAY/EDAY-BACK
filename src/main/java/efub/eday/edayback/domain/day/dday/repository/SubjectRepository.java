package efub.eday.edayback.domain.day.dday.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import efub.eday.edayback.domain.day.dday.entity.Dday;
import efub.eday.edayback.domain.day.dday.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	Optional<Subject> findByDday(Dday dday);
}
