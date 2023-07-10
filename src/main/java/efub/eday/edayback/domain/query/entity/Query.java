package efub.eday.edayback.domain.query.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Query {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "query_id")
	private Long id;
}
