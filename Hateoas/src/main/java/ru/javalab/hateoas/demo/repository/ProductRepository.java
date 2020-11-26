package ru.javalab.hateoas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javalab.hateoas.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
