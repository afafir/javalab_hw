package ru.javalab.hateoas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javalab.hateoas.demo.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
