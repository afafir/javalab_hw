package com.mongo.query_dsl.demo.repository;

import com.mongo.query_dsl.demo.model.Consumer;
import com.mongo.query_dsl.demo.model.QConsumer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface ConsumerRepository
        extends MongoRepository<Consumer, String>, QuerydslPredicateExecutor<Consumer>, QuerydslBinderCustomizer<QConsumer> {
    @Override
    default void customize(QuerydslBindings bindings, QConsumer qConsumer){

    }

}
