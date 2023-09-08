package za.co.anycompany.service;

import za.co.anycompany.dto.NewOrderDTO;
import za.co.anycompany.dto.OrderDTO;

import java.util.UUID;

public interface OrderService {
    UUID placeOrder(NewOrderDTO order);

    OrderDTO getOrder(UUID id);
}
