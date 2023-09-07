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
@Entity(name = "ORDER")
public class OrderEntity extends BaseEntity {
    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    @Column(name = "VAT", nullable = false)
    private Double vat;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    private CustomerEntity customer;
}
