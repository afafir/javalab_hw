package ru.javalab.hateoas.demo.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.javalab.hateoas.demo.model.user.Consumer;
import ru.javalab.hateoas.demo.model.user.Volunteer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_consumer", nullable = false)
    @NotNull
    private Consumer consumer;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_performer")
    private Volunteer performer;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToMany(
            fetch = FetchType.LAZY
    )
    private List<Product> productList;
    public void confirm(){
        if (this.status.equals(Status.ON_EXECUTION)){
            this.status = Status.DONE;
        } else {
            throw new IllegalStateException("Нельзя подветрдить выполнение, если заказ еще не принят");
        }
    }

    public void setOnExecution(Volunteer performer){
        if(this.status.equals(Status.NEW)){
            this.status = Status.ON_EXECUTION;
            this.performer = performer;
        } else {
            throw new IllegalStateException("Нельзя подветрдить выполнение, если заказ еще не принят");
        }
    }

    public void addProduct(Product product){
        if (this.status.equals(Status.NEW)){
            productList.add(product);
        }else {
            throw new IllegalStateException();
        }
    }

}
