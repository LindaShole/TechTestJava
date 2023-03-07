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
    public String get(@PathVariable int id, @RequestParam(name="name", required=false, defaultValue="Xolisani") String name, Model model){
        //model.addAttribute("name", name);
        Order order = orderService.getOrderById(id);
        model.addAttribute("orderId", order.getOrderId());
        model.addAttribute("amount", order.getAmount());
        model.addAttribute("VAT", order.getVAT());
        model.addAttribute("customerId", order.getCustomerId());
       /* return "customer"; // return form

        Order order = orderService.get(id);
        if (order==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);*/
        return "order";
    }

    @GetMapping("/orders/{id}/customer/{customerId}")
    public String getCustomerOrders(@PathVariable int customerId, @RequestParam(name="name", required=false, defaultValue="Xolisani") String name, Model model){
        //model.addAttribute("name", name);
        List <Order> orders = orderService.getOrderByCustomerId(customerId);
        model.addAttribute("orders", orders);
        //Customer customer = new Customer();

      /*  model.addAttribute("orderId", order.getOrderId());
        model.addAttribute("amount", order.getAmount());
        model.addAttribute("VAT", order.getVAT());
        model.addAttribute("customerId", order.getCustomerId());
       /* return "customer"; // return form

        Order order = orderService.get(id);
        if (order==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);*/
        return "orders";
    }

    // 3. http://localhost:8081/orders/{id}
    @DeleteMapping("/orders/{id}")
    public void delete(@PathVariable Integer id){
        Order order = orderService.remove(id);
        if (order==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // 4.0. http://localhost:8081/order "get the form"
    @GetMapping("/order")
    public String orderHere(Model model){
        // get one order
        Order order = new Order();
        model.addAttribute("order", order);

        // get all order
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);

        return "place";
    }

    // 4.1 http://localhost:8081/orders
    @PostMapping("/orders")
    public String create(@RequestBody Order order){ //@Valid
        //int customerId = order.getCustomerId();

        order.setAmount(1165.36);
        order.setOrderId(4);
        //Integer.parseInt(UUID.randomUUID().toString())
        orderService.placeOrder( order, 11);
        return "orders";
    }

    // 4.1 http://localhost:8081/orders
    @PostMapping("/order")
    public String orderPlaced(@ModelAttribute("order") Order order){
        return "ordered";
    }

    // 5. http://localhost:8081/orders/{id}  *test the POST HERE*
    @PostMapping("/myorders")
    public void placeOrder(@RequestBody Order order){
        order.setAmount(299);
       // order.setCustomerId(11);
        orderService.placeOrder(order, 11);
    }
}
