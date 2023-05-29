package za.co.anycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.anycompany.model.Order;
import za.co.anycompany.service.OrderService;

import java.util.List;

@RestController(value = "order")
@RequestMapping(value = "order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public List<Order> getOrders() {

        return orderService.getOrders();
    }


    @PostMapping("/add")
    public boolean addOrder(@RequestBody Order order) {

        Long customerId = order.getCustomer().getId();

        return orderService.placeOrder(order, customerId);
    }
}
