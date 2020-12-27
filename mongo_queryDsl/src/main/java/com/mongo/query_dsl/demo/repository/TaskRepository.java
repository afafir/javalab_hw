package com.mongo.query_dsl.demo.repository;

import com.mongo.query_dsl.demo.model.QConsumer;
import com.mongo.query_dsl.demo.model.QTask;
import com.mongo.query_dsl.demo.model.Task;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface TaskRepository
 extends MongoRepository<Task, String>, QuerydslPredicateExecutor<Task>, QuerydslBinderCustomizer<QTask> {

    @Override
    default void customize (QuerydslBindings bindings, QTask qTask){
        bindings.bind(qTask.product.any().name).as("product.name").first(
                StringExpression::containsIgnoreCase
        );
    }

}
