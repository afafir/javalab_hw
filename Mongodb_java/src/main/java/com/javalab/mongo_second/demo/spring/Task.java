package com.javalab.mongo_second.demo.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private String _id;
    private String description;
    private List<Product> product;
}
