package za.co.anycompany.dto;

import java.util.List;

public class CustomerOrdersDto {
    private CustomerDto customer;
    private List<OrderDto> orders;

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }
}
