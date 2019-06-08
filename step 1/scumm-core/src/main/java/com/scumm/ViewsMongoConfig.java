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
        basePackages = "com.scumm.views.repositories",
        mongoTemplateRef = "mongoViewsTemplate")

public class ViewsMongoConfig {

    @Primary
    @Bean(name = "mongoViewsTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("mongoViewsFactory") MongoDbFactory mongoFactory) {
        return new MongoTemplate(mongoFactory);
    }

    @Primary
    @Bean("mongoViewsFactory")
    public MongoDbFactory mongoFactory(@Qualifier("mongoViewsProperties") MongoProperties mongoProperties) {
        MongoClientURI uri = new MongoClientURI(mongoProperties.getUri());
        return new SimpleMongoDbFactory(new MongoClient(uri), mongoProperties.getDatabase());
    }

    @Bean(name = "mongoViewsProperties")
    @ConfigurationProperties(prefix = "mongodb.views")
    @Primary
    public MongoProperties properties() {
        return new MongoProperties();
    }
}
