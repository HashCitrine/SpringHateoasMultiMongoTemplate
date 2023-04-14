package hateoas.mongo.api.config.common;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class CommonService<T extends CommonCollection> {

	public Page<T> getCollection(String name, Pageable pageable) {
		return getRepository().findAllByNameRegex(name, pageable);
	}

	public Optional<T> getDocument(Long id) {
		return getRepository().findById(id);
	}

	public abstract CommonRepository getRepository();
}
