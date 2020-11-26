package com.javalab.mongo_hateoas.demo.repository;

import com.javalab.mongo_hateoas.demo.model.Consumer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsumerRepository extends MongoRepository<Consumer, String> {
}
