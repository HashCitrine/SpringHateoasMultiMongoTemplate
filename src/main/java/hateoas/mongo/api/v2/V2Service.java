package hateoas.mongo.api.v2;

import org.springframework.stereotype.Service;

import hateoas.mongo.api.config.common.CommonRepository;
import hateoas.mongo.api.config.common.CommonService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class V2Service extends CommonService<V2> {
	private final CommonRepository<V2> repository;
	public CommonRepository<V2> getRepository() {
		return repository;
	}
}
