package ru.javalab.hateoas.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.javalab.hateoas.demo.model.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
