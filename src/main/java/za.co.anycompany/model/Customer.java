package za.co.anycompany.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "customer")
@Table(name = "customer")
public class Customer {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter
    @Setter
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Getter
    @Setter
    @Column(name = "country", nullable = false)
    private String country;
    @Getter
    @Setter
    @Column(name = "dateOfBirth", nullable = false)
    private Date dateOfBirth;

    @Getter
    @Setter
    @JsonManagedReference
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders;

    public Customer() {
    }

    public Customer(Long id, String name, String country, Date dateOfBirth, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.orders = orders;
    }

    public boolean addOrder(Order order){
        return orders.add(order);

    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", orders=" + orders +
                '}';
    }
}
