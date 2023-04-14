package hateoas.mongo.api.config.mongo;

import static hateoas.mongo.api.util.Constants.*;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
	basePackages = packageDirectory + v1,
	mongoTemplateRef = v1 + mongoTemplate)
public class PrimaryConfig {
}
