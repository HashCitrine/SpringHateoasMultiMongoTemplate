package hateoas.mongo.api.v1;

import static hateoas.mongo.api.util.Constants.*;

import org.springframework.data.mongodb.core.mapping.Document;

import hateoas.mongo.api.config.common.CommonCollection;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Document(v1)
public class V1 extends CommonCollection {

	private final String name;
	private final String v1Property;

	public V1(Long id, String name, String v1Property) {
		super(id);
		this.name = name;
		this.v1Property = v1Property;
	}

	@Override
	public String getCollectionName() {
		return v1;
	}
}
