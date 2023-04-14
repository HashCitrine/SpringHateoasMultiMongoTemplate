package hateoas.mongo.api.config.common.dto;

import java.util.Collection;

import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResponseModel<T> extends RepresentationModel<ResponseModel<T>> {
	private final Collection<T> data;
	private final PagedModel.PageMetadata page;
}
