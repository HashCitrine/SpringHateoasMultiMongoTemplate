package hateoas.mongo.api.config.common.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestDto extends CommonDto {

	@Schema(description = "조회 페이지", example = "1")
	private final int page;

	@Schema(description = "조회 페이지 사이즈", example = "10")
	private final int size;

	@Schema(description = "조회 정렬 기준 필드", example = "id")
	private final String field;

	@Schema(description = "조회 정렬 방법", example = "ASC")
	private final Sort.Direction direction;

	@JsonIgnore
	public Pageable getPageable() {
		Sort sort = (field == null) ? Sort.unsorted()
			: (direction == null) ? Sort.by(field) : Sort.by(direction, field);
		return PageRequest.of(page - 1, size, sort);
	}
}
