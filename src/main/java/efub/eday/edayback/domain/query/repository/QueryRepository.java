package efub.eday.edayback.domain.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import efub.eday.edayback.domain.query.entity.Query;

public interface QueryRepository extends JpaRepository<Query, Integer> {
}
