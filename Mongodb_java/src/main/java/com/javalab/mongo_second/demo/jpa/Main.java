package com.javalab.mongo_second.demo.jpa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
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
        System.out.println(taskRepository.find("new", "moloko", 2));
    }
}
