package za.co.anycompany.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name="Customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable=false, nullable=false)
    private Long id;

    @Column(name = "country", updatable=false, nullable=false)
    private String country;

    @Column(name = "name", updatable=false, nullable=false)
    private String name;

    @Column(name = "dateOfBirth", updatable=false, nullable=false)
    private Date dateOfBirth;

    @OneToMany(mappedBy = "id")
    private List<Order> order;

}
