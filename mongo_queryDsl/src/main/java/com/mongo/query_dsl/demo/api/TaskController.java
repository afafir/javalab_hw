package com.mongo.query_dsl.demo.api;

import com.mongo.query_dsl.demo.model.Task;
import com.mongo.query_dsl.demo.repository.TaskRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;

    @GetMapping("/tasks/search")
    public ResponseEntity<List<Task>> searchByPredicate(@QuerydslPredicate(root = Task.class, bindings = TaskRepository.class) Predicate predicate){
        return ResponseEntity.ok(
                StreamSupport.stream(taskRepository.findAll(predicate).spliterator(), true)
                    .collect(Collectors.toList())
        );
    }
}
