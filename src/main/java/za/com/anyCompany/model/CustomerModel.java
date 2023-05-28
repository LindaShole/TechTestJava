package za.com.anyCompany.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Customer")
    public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;
    @Column(name="date_Of_Birth")
    private Timestamp dateOfBirth;
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private AddressModel shippingAddress;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<OrderModel> orders;
}

