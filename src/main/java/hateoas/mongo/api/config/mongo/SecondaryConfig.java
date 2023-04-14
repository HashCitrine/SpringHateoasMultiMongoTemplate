package hateoas.mongo.api.config.mongo;

import static hateoas.mongo.api.util.Constants.*;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
	basePackages = packageDirectory + v2,
	mongoTemplateRef = v2 + mongoTemplate)
public class SecondaryConfig {
}
