package hateoas.mongo.api.config.common;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

@Getter
public abstract class CommonCollection {

	@Id
	private final Long id;

	public CommonCollection(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public abstract String getCollectionName();

	@JsonIgnore
	public String[] getDbrefNames() {
		return new String[0];
	}
}
