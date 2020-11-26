package ru.javalab.hateoas.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javalab.hateoas.demo.model.Product;
import ru.javalab.hateoas.demo.model.Status;
import ru.javalab.hateoas.demo.model.Task;
import ru.javalab.hateoas.demo.model.user.Volunteer;
import ru.javalab.hateoas.demo.repository.ConsumerRepository;
import ru.javalab.hateoas.demo.repository.ProductRepository;
import ru.javalab.hateoas.demo.repository.TaskRepository;
import ru.javalab.hateoas.demo.repository.VolunteerRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService
{

    private final TaskRepository taskRepository;
    private final VolunteerRepository volunteerRepository;
    private final ProductRepository productRepository;
    private final ConsumerRepository consumerRepository;


    @Override
    @Transactional
    public Task addProductToTask(Long taskId, Long productId) {
        Task task = getTask(taskId);
        Product product = getProduct(productId);
        task.addProduct(product);
        return task;
    }

    @Override
    @Transactional
    public Task acceptTask(Long volunteerId, Long taskId) {
        Task task = getTask(taskId);
        Volunteer volunteer = getVolunteer(volunteerId);
        if (task.getPerformer().getId().equals(volunteerId)){
            task.setOnExecution(volunteer);
            return task;
        } else throw new IllegalStateException("блабла");
    }


    @Override
    @Transactional
    public Task confirmTask(Long volunteerId, Long taskId) {
        Task task = getTask(taskId);
        if (task.getPerformer().getId().equals(volunteerId)){
            task.confirm();
            return task;
        } else throw new IllegalStateException("блабла");    }

    @Override
    public Task createTask(Long consumerId) {
        Task task = Task.builder()
                .status(Status.NEW)
                .consumer(consumerRepository.getOne(consumerId))
                .build();
        return taskRepository.save(task);
    }


    private Task getTask (Long id){
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()){
            throw new IllegalArgumentException("Task not found");
        }
        return taskOptional.get();
    }

    private Product getProduct (Long id){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()){
            throw new IllegalArgumentException("Product not found");
        }
        return productOptional.get();
    }

    private Volunteer getVolunteer (Long id){
        Optional<Volunteer> volunteerOptional = volunteerRepository.findById(id);
        if (volunteerOptional.isEmpty()){
            throw new IllegalArgumentException("Volunteer not found");
        }
        return volunteerOptional.get();
    }


}
