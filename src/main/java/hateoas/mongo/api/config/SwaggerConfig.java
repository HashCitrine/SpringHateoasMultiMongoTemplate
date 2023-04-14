package hateoas.mongo.api.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hateoas.mongo.api.util.Constants;

@Configuration
public class SwaggerConfig {

	private String version;
	private String[] paths;

	@Bean
	public GroupedOpenApi apiV1() {
		version = Constants.v1;
		paths = new String[] {Constants.apiPrefix + version + Constants.slash + "**"};
		return GroupedOpenApi.builder()
			.group(version)
			.pathsToMatch(paths)
			.build();
	}

	@Bean
	public GroupedOpenApi apiV2() {
		version = Constants.v2;
		paths = new String[] {Constants.apiPrefix + version + Constants.slash + "**"};
		return GroupedOpenApi.builder()
			.group(version)
			.pathsToMatch(paths)
			.build();
	}
}
