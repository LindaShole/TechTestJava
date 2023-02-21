package za.co.anycompany.anycompany.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.co.anycompany.anycompany.model.Order;
import za.co.anycompany.anycompany.service.OrderService;

import java.util.*;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String hello(){
        return "Hello Xolisani";
    }

    @GetMapping("/orders")
    public Collection<Order> get(){
        return orderService.get();
    }

    @GetMapping("/orders/{id}")
    public Order get(@PathVariable int id){
        Order order = orderService.get(id);
        if (order==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return order;
    }

    @DeleteMapping("/orders/{id}")
    public void delete(@PathVariable Integer id){
        Order order = orderService.remove(id);
        if (order==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/orders")
    public Order create(@RequestBody Order order){ //@Valid
        order.setOrderId(Integer.parseInt(UUID.randomUUID().toString()));
        orderService.save(order.getOrderId(), order);
        return order;
    }

    @PostMapping("/myorders")
    public void placeOrder(@RequestBody Order order){
        orderService.placeOrder(order, 1);
    }
}
