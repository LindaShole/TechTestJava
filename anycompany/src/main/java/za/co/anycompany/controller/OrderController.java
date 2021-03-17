package za.co.anycompany.controller;

import za.co.anycompany.model.Order;
import za.co.anycompany.service.OrderService;

public class OrderController {

    private OrderService orderService =  new OrderService();

    public Order placeOrder(Order order){
         orderService.placeOrder(order);
         return order;
    }

}
