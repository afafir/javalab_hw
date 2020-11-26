package com.javalab.mongo_hateoas.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "tasks")
public class Task {
    @Id
    private String _id;
    private String description;
    private String status;
    private List<Product> product;
    @DBRef
    private Consumer consumer;
}
