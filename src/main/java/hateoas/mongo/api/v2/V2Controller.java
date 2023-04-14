package hateoas.mongo.api.v2;

import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hateoas.mongo.api.config.common.CommonController;
import hateoas.mongo.api.config.common.CommonService;
import hateoas.mongo.api.util.Constants;

@RestController(Constants.v2 + Constants.controller)
@RequestMapping(value = Constants.apiPrefix + Constants.v2, produces = MediaTypes.HAL_JSON_VALUE)
public class V2Controller extends CommonController<V2> {

	public V2Controller(CommonService<V2> service) {
		super(service);
	}
}
