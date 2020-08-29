package za.co.anycompany.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@JsonInclude
@Entity
@Table(name = "order_table")
public class Order  implements Serializable {
    @Id
    private int orderId;
    private double amount;
    private double vat;

}
