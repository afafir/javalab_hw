package com.javalab.mongo_second.demo.jpa;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.javalab.mongo_second.demo.jpa")
public class Config {
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(MongoClients.create(), "javalab1");
    }
}
