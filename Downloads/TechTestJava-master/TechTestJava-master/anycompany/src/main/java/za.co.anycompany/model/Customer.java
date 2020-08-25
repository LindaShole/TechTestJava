package za.co.anycompany.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Customer {
    private int customerId;
    private String name;
    private String country;
    private Date dateOfBirth;

    private List<Order> orders;

    public int getCustomerId() {
        return customerId;
    }
}
