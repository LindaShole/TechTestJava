package za.com.anyCompany.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orderdetails")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @Column(name="amount")
    private double amount;
    @ManyToOne
    @JoinColumn(name = "fk_customer_id")
    private CustomerModel customer;

    public OrderModel(double amount, CustomerModel customer) {
        this.amount = amount;
        this.customer=customer;
    }
}


