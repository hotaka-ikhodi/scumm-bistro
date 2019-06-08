package com.scumm;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.scumm.core.domain.repositories",
        mongoTemplateRef = "mongoCoreTemplate")

public class CoreMongoConfig {

    @Primary
    @Bean(name = "mongoCoreTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("mongoCoreFactory") MongoDbFactory mongoFactory) {
        return new MongoTemplate(mongoFactory);
    }

    @Primary
    @Bean("mongoCoreFactory")
    public MongoDbFactory mongoFactory(@Qualifier("mongoCoreProperties") MongoProperties mongoProperties) {
        MongoClientURI uri = new MongoClientURI(mongoProperties.getUri());
        return new SimpleMongoDbFactory(new MongoClient(uri), mongoProperties.getDatabase());
    }

    @Bean(name = "mongoCoreProperties")
    @ConfigurationProperties(prefix = "mongodb.core")
    @Primary
    public MongoProperties properties() {
        return new MongoProperties();
    }
}
