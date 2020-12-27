package com.mongo.query_dsl.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "tasks")
public class Task implements Serializable {
    @Id
    private String _id;
    private String description;
    private String status;
    private List<Product> product;
    @DBRef
    private Consumer consumer;
}
