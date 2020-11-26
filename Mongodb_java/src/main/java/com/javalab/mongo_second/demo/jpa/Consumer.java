package com.javalab.mongo_second.demo.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "consumers")
public class Consumer {
    @Id
    private String _id;
    private String disease;
    private String name;
    private Integer years;

}
