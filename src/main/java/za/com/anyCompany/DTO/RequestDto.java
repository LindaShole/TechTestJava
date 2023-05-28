package za.com.anyCompany.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.com.anyCompany.model.CustomerModel;
import za.com.anyCompany.model.ProductModel;
import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDto implements Serializable {
    private int orderId;
    private double amount;
    private CustomerModel customer;
    private List<ProductModel> products;
}
