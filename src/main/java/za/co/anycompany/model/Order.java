package za.co.anycompany.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity(name="\"Order\"")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    private double amount;
    private double VAT;
	

}
