package za.co.anycompany.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.anycompany.model.Order;
import za.co.anycompany.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    public OrderController()
    {

    }

    @PostMapping
    ResponseEntity placeOrder(@RequestBody Order order){

        boolean isCreated = orderService.placeOrder(order, order.getCustomerId());

        if(isCreated)
        {
            return ResponseEntity.created(null).build();
        }
        else
        {
            return ResponseEntity.badRequest().build();
        }
    }
}
