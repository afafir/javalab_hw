package ru.javalab.hateoas.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.javalab.hateoas.demo.model.Address;
import ru.javalab.hateoas.demo.model.Status;
import ru.javalab.hateoas.demo.model.Task;
import ru.javalab.hateoas.demo.model.user.Consumer;
import ru.javalab.hateoas.demo.repository.AddressRepository;
import ru.javalab.hateoas.demo.repository.ConsumerRepository;
import ru.javalab.hateoas.demo.repository.TaskRepository;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);

        ConsumerRepository consumerRepository = run.getBean(ConsumerRepository.class);
        AddressRepository addressRepository = run.getBean(AddressRepository.class);
        TaskRepository taskRepository = run.getBean(TaskRepository.class);
        Address address = Address.builder()
                .address("addr")
                .city("city")
                .build();
        addressRepository.save(address);
        Consumer consumer = Consumer.builder()
                .address(address)
                .disease("disease")
                .name("name")
                .build();
        consumerRepository.save(consumer);
        Task task = Task.builder()
                .status(Status.NEW)
                .consumer(consumer)
                .build();
        taskRepository.save(task);
    }

}
