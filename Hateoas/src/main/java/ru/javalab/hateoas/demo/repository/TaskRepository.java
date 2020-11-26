package ru.javalab.hateoas.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.javalab.hateoas.demo.model.Task;
import ru.javalab.hateoas.demo.model.user.Consumer;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "task", path = "task")
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    @RestResource(path = "new", rel = "new")
    @Query("from Task task where task.status = 'NEW'")
    Page<Task> findAllNew(Pageable pageable);

    @RestResource(path = "byConsumer", rel = "consumer")
    List<Task> findAllByConsumer(Consumer consumer);

    @Override
    @RestResource(exported = false)
    public Task save(Task task);


}
