package za.co.anycompany.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.anycompany.model.Order;
import za.co.anycompany.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/{customerId}")
    ResponseEntity<List<Order>> getOrders(@PathVariable(value = "customerId") int customerId){
        return new ResponseEntity<>(orderService.findOrderByOrderId(customerId), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Boolean> placeOrder(@RequestBody Order order){
         return new ResponseEntity<>(orderService.placeOrder(order, order.getCustomerId()), HttpStatus.OK);
    }
}
