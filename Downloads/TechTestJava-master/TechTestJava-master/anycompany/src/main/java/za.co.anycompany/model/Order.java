package za.co.anycompany.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Order {

    private int orderId;
    private double amount;
    private double VAT;
    private int customerId;

    public int getOrderId() {
        return orderId;
    }

}