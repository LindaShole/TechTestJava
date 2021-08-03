package za.co.anycompany.dto;

import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.List;

public class OrdersDto extends AbstractPersistable<Long> {
    private Long customerId;
    List<OrderDto> orders;

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
