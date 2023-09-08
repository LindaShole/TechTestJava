package za.co.anycompany.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "order")
public class OrderEntity extends BaseEntity {
    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "vat", nullable = false)
    private Double vat;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;
}
