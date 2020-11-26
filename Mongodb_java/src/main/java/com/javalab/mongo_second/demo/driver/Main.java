package com.javalab.mongo_second.demo.driver;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class Main {
    public static void main(String[] args) {
        MongoClient client = MongoClients.create();
        MongoDatabase mongoDatabase = client.getDatabase("javalab");
        MongoCollection<Document> collection = mongoDatabase.getCollection("tasks");
        collection.find()
                .forEach(x -> System.out.println(x.getString("description")));

        Document searchQuery = new Document();
        searchQuery
                .append("volunteer", new Document("$exists", true))
                .append("$or", Arrays.asList(
                        new Document("product", new Document("$size", 2)),
                        new Document("product.name", "moloko")));

        FindIterable<Document> documents = collection.find(searchQuery)
                .projection(new Document("product", 1)
                        .append("volunteer", 1)
                        .append("description", 1)
                        .append("_id", 0));

        FindIterable<Document> documents1 = collection.find(
                and(new Document("volunteer", new Document("$exists", true)),
                        or(new Document("product.name", "moloko"), size("product", 2)))
        ).projection(fields(include("volunteers", "product", "description"), excludeId()));


        for (Document document : documents) {
            System.out.println(document.toJson());
        }
        for (Document document : documents1) {
            System.out.println(document.toJson());
        }

    }
}
