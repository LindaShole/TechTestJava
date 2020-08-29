package za.co.anycompany.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "customer_table")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String name;
    private String country;
    private Date dateOfBirth;
    @OneToMany(targetEntity = Order.class,fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "customer_fk", referencedColumnName = "customerId")
    private List<Order> orders;

}
