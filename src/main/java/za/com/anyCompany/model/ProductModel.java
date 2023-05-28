package za.com.anyCompany.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="productName")
    private String productName;
    @Column(name="price")
    private double price;
    @Column(name="quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "fk_customer_id")
    private CustomerModel customer;
}
