package za.co.anycompany.anycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.co.anycompany.anycompany.model.Customer;
import za.co.anycompany.anycompany.model.Order;
import za.co.anycompany.anycompany.service.OrderService;

import java.util.*;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 1. http://localhost:8081/orders
    @GetMapping("/orders")
    public String get(@RequestParam(name="name", required=false, defaultValue="User") String name, Model model){
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);

        return "orders";
    }

    // 2. http://localhost:8081/orders/{id}
    @GetMapping("/orders/{id}")
    public Order get(@PathVariable int id){
        Order order = orderService.get(id);
        if (order==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return order;
    }

    // 3. http://localhost:8081/orders/{id}
    @DeleteMapping("/orders/{id}")
    public void delete(@PathVariable Integer id){
        Order order = orderService.remove(id);
        if (order==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // 4. http://localhost:8081/orders
    @PostMapping("/orders")
    public Order create(@RequestBody Order order){ //@Valid
        order.setOrderId(Integer.parseInt(UUID.randomUUID().toString()));
        orderService.save(order.getOrderId(), order);
        return order;
    }

    // 5. http://localhost:8081/orders/{id}  *test the POST HERE*
    @PostMapping("/myorders")
    public void placeOrder(@RequestBody Order order){
        order.setAmount(299);
       // order.setCustomerId(11);
        orderService.placeOrder(order, 11);
    }
}
