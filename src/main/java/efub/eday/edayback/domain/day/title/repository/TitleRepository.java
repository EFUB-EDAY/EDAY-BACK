package efub.eday.edayback.domain.day.title.repository;

import efub.eday.edayback.domain.day.title.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {
    Optional<Title> findBySubjectId(Long subjectId);
}
