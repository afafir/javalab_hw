package ru.javalab.hateoas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javalab.hateoas.demo.model.user.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
}
