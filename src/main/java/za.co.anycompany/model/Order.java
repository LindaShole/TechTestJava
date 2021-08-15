package za.co.anycompany.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="Orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable=false, nullable=false)
    private int id;

    @Column(name = "VAT", nullable=false)
    private double VAT;

    @Column(name = "amount", nullable=false)
    private double amount;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;


}
