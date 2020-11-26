package com.javalab.mongo_second.demo.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsumerRepository extends MongoRepository<Consumer, String> {
}
