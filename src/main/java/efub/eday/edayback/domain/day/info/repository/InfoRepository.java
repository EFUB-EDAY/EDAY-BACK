package efub.eday.edayback.domain.day.info.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import efub.eday.edayback.domain.day.info.entity.Info;

public interface InfoRepository extends JpaRepository<Info, Long> {
	Optional<Info> findByDDay_DDay(int dDay);
}
