package za.co.anycompany.mappers;

import za.co.anycompany.dtos.OrderDTO;
import za.co.anycompany.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public OrderDTO fromEntityToDTO(Order order)
    {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setCustomerId(order.getCustomerId());
        orderDTO.setAmount(order.getAmount());
        orderDTO.setVAT(order.getVAT());

        return orderDTO;
    }

    public List<OrderDTO> fromEntitiesToDTOs(List<Order> orders)
    {
        List<OrderDTO> orderDTOs = new ArrayList<>();

        for(Order order : orders){
            orderDTOs.add(fromEntityToDTO(order));
        }

        return orderDTOs;
    }
}
