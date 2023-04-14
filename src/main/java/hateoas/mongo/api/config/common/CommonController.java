package hateoas.mongo.api.config.common;

import static hateoas.mongo.api.util.Constants.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import hateoas.mongo.api.config.common.dto.RequestDto;
import hateoas.mongo.api.config.common.dto.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommonController<T extends CommonCollection> {

	protected final CommonService<T> service;

	@GetMapping
	@Operation(summary = "Collection 조회")
	public ResponseEntity<ResponseModel<EntityModel<T>>> getCollection(@ParameterObject RequestDto dto) {
		String name = dto.getName();
		name = (StringUtils.hasLength(name)) ? name : "";

		List<EntityModel<T>> list = new ArrayList<>();
		Page<T> collections = service.getCollection(name, dto.getPageable());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getCollection(dto));
		PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
			dto.getSize(), dto.getPage(), collections.getTotalElements());

		for (T document : collections) {
			EntityModel<T> resDto = EntityModel.of(document);
			WebMvcLinkBuilder contentLink = linkTo(methodOn(this.getClass()).getDocument(document.getId()));
			resDto.add(contentLink.withSelfRel());

			list.add(resDto);
		}

		ResponseModel<EntityModel<T>> responses = new ResponseModel(list, metadata);
		responses.add(link.withSelfRel());

		return list.size() > 0 ? ResponseEntity.ok(responses) : ResponseEntity.notFound().build();
	}

	@GetMapping(idPostfix)
	@Operation(summary = "Document 조회")
	public ResponseEntity<ResponseModel<EntityModel<T>>> getDocument(
		@Parameter(description = "Document ID", example = "1") @PathVariable("id") Long id) {

		Optional<T> optionalDocument = service.getDocument(id);

		if (optionalDocument.isPresent()) {
			T document = optionalDocument.get();
			List list = new ArrayList();
			list.add(document);
			ResponseModel<EntityModel<T>> resource = new ResponseModel(list, null);

			resource.add(linkTo(methodOn(this.getClass()).getDocument(id))
				.slash(id)
				.withRel(document.getCollectionName()));

			for (String dbref : document.getDbrefNames()) {
				resource.add(linkTo(methodOn(this.getClass()).getDocument(id))
					.slash(dbref)
					.withRel(dbref));
			}

			return ResponseEntity.ok(resource);
		}

		return ResponseEntity.notFound().build();
	}
}
