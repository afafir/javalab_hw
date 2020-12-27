package com.mongo.query_dsl.demo;

import com.mongo.query_dsl.demo.model.Consumer;
import com.mongo.query_dsl.demo.model.Product;
import com.mongo.query_dsl.demo.model.Task;
import com.mongo.query_dsl.demo.repository.ConsumerRepository;
import com.mongo.query_dsl.demo.repository.TaskRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.mongo.query_dsl.demo.repository")
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        TaskRepository taskRepository = context.getBean(TaskRepository.class);
        ConsumerRepository consumerRepository = context.getBean(ConsumerRepository.class);
        Consumer consumer = Consumer.builder()
                .disease("disease")
                .name("name")
                .years(124)
                .build();
        Consumer saved = consumerRepository.save(consumer);
        Task task = Task.builder()
                .description("desc")
                .status("new")
                .consumer(consumer)
                .product(Arrays.asList(
                        Product.builder()
                                .count(1)
                                .name("123moloko")
                                .build(),
                        Product.builder()
                                .count(2)
                                .name("nemoloko")
                                .count(33)
                                .build()))
                .build();
        Task task1 = Task.builder()
                .description("desc")
                .status("new")
                .consumer(consumer)
                .product(Arrays.asList(
                        Product.builder()
                                .count(1)
                                .name("moloko")
                                .build()))
                .build();
        taskRepository.save(task);
        taskRepository.save(task1);
    }

}
