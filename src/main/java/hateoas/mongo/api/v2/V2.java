package hateoas.mongo.api.v2;

import static hateoas.mongo.api.util.Constants.*;

import org.springframework.data.mongodb.core.mapping.Document;

import hateoas.mongo.api.config.common.CommonCollection;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Document(v2)
public class V2 extends CommonCollection {

	private final String name;
	private final String v2Property;

	public V2(Long id, String name, String v2Property) {
		super(id);
		this.name = name;
		this.v2Property = v2Property;
	}

	@Override
	public String getCollectionName() {
		return v1;
	}
}
