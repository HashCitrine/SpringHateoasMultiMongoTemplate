package hateoas.mongo.api.v1;

import org.springframework.stereotype.Service;

import hateoas.mongo.api.config.common.CommonRepository;
import hateoas.mongo.api.config.common.CommonService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class V1Service extends CommonService<V1> {
	private final CommonRepository<V1> repository;
	public CommonRepository<V1> getRepository() {
		return repository;
	}
}
