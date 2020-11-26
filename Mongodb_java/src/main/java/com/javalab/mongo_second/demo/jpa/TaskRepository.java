package com.javalab.mongo_second.demo.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    @Query(value = "{status : ?0, $or: [{product : {$size: ?2}}, {'product.name' : ?1}]}")
    List<Task> find(@Param("status") String status, @Param("name") String productName, @Param("size") long size);
}
