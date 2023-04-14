package hateoas.mongo.api.config.common;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommonRepository<T extends CommonCollection> extends MongoRepository<T, Long> {
	Optional<T> findById(Long id);

	Page<T> findAllByNameRegex(String name, Pageable pageable);
}
