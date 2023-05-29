package za.co.anycompany.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Getter
    @Setter
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    public Order() {
    }

    public Order(Long id, double amount, double VAT, Customer customer) {
        this.id = id;
        this.amount = amount;
        this.VAT = VAT;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", amount=" + amount +
                ", VAT=" + VAT +
                ", customer=" + customer +
                '}';
    }
}
