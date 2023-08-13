package efub.eday.edayback.domain.day.title.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import efub.eday.edayback.domain.day.dday.entity.Dday;
import efub.eday.edayback.domain.day.title.entity.Title;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {
	Optional<Title> findBySubjectId(Integer subjectId);

	Optional<Title> findBySubject_Dday(Dday dday);
}
