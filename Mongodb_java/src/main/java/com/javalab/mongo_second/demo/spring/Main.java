package com.javalab.mongo_second.demo.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        MongoTemplate template = context.getBean(MongoTemplate.class);


        // db.courses.find({active: true, $or: [{keywords: 'java core'}, {studentsCount: {$lt: 35}}]})
        List<Task> tasks = template.find(new Query(
                where("volunteer").exists(true)
                        .orOperator(where("product").size(2)
                        ,where("product.name").is("moloko"))), Task.class, "tasks");
        System.out.println(tasks);


    }
}
