package za.co.anycompany.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
    @Column(name = "name", nullable = false)
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_order_id",referencedColumnName = "id")
    private Order order;

    public Customer() {
    }

    public Customer(Long id, String name, String country, Date dateOfBirth, Order order) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.order = order;
    }
}
