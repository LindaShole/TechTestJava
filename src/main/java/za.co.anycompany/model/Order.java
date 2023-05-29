package za.co.anycompany.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "customer_order")
@Table(name = "customer_order")
public class Order {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter
    @Setter
    @Column(name = "amount", nullable = false)
    private double amount;

    @Getter
    @Setter
    @Column(name = "VAT", nullable = false)
    private double VAT;

    @OneToOne(mappedBy = "order")
    private Customer customer;

    public Order() {
    }

    public Order(Long id, double amount, double VAT) {
        this.id = id;
        this.amount = amount;
        this.VAT = VAT;
    }
}
