package hateoas.mongo.api.config.mongo;

import static hateoas.mongo.api.util.Constants.*;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MultipleDbConfig {
	private final String properties = "Properties";
	private final String mongoProperty = "spring.data.mongodb.";

	@Primary
	@Bean(name = v1 + properties)
	@ConfigurationProperties(prefix = mongoProperty + v1)
	public MongoProperties getV1Props() throws Exception {
		return new MongoProperties();
	}

	@Primary
	@Bean
	public MongoDatabaseFactory v1MongoDatabaseFactory(MongoProperties mongo) throws Exception {
		return new SimpleMongoClientDatabaseFactory(mongo.getUri());
	}

	@Primary
	@Bean(name = v1 + mongoTemplate)
	public MongoTemplate newdb1MongoTemplate() throws Exception {
		return new MongoTemplate(v1MongoDatabaseFactory(getV1Props()));
	}

	@Bean(name = v2 + properties)
	@ConfigurationProperties(prefix = mongoProperty + v2)
	public MongoProperties getV2Props() throws Exception {
		return new MongoProperties();
	}

	@Bean
	public MongoDatabaseFactory v2MongoDatabaseFactory(MongoProperties mongo) throws Exception {
		return new SimpleMongoClientDatabaseFactory(mongo.getUri());
	}

	@Bean(name = v2 + mongoTemplate)
	public MongoTemplate newdb2MongoTemplate() throws Exception {
		return new MongoTemplate(v2MongoDatabaseFactory(getV2Props()));
	}

}
