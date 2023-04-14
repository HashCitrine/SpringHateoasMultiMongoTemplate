package hateoas.mongo.api.v1;

import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hateoas.mongo.api.config.common.CommonController;
import hateoas.mongo.api.config.common.CommonService;
import hateoas.mongo.api.util.Constants;

@RestController(Constants.v1 + Constants.controller)
@RequestMapping(value = Constants.apiPrefix + Constants.v1, produces = MediaTypes.HAL_JSON_VALUE)
public class V1Controller extends CommonController<V1> {

	public V1Controller(CommonService<V1> service) {
		super(service);
	}
}
