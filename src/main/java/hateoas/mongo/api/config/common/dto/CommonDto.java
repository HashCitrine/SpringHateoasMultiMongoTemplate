package hateoas.mongo.api.config.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonDto {

	@Schema(description = "Document 이름", required = false)
	private String name;
}
