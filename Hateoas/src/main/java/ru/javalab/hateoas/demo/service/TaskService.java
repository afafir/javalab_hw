package ru.javalab.hateoas.demo.service;

import ru.javalab.hateoas.demo.model.Task;
import ru.javalab.hateoas.demo.model.user.Consumer;

public interface TaskService {
    Task addProductToTask(Long taskId, Long productId);
    Task acceptTask(Long volunteerId, Long taskId);
    Task confirmTask(Long volunteerId, Long taskId);
    Task createTask (Long consumerId);
}
