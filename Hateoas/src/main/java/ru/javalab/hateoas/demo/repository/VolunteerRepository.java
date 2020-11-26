package ru.javalab.hateoas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javalab.hateoas.demo.model.user.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
}
