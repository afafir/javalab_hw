package ru.javalab.hateoas.demo.model.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.javalab.hateoas.demo.model.Address;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Consumer extends User {
    @Column(name = "disease", nullable = false)
    private String disease;
    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    private Address address;
}
