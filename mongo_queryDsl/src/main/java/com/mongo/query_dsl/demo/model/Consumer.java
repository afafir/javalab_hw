package com.mongo.query_dsl.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "consumers")
public class Consumer implements Serializable {
    @Id
    private String _id;
    private String disease;
    private String name;
    private Integer years;
}
