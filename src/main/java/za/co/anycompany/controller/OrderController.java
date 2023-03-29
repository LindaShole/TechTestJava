package za.co.anycompany.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.co.anycompany.model.Order;
import za.co.anycompany.service.OrderService;

import java.util.*;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 1.1 http://localhost:8081/orders
    @GetMapping("/orders")
    public String get(@RequestParam(name="name", required=false, defaultValue="User") String name, Model model){
        List<Order> orders = orderService.getAllOrders();
        if(orders.size()==0)
            return "error";
        else{
            model.addAttribute("orders", orders);
            return "orders";
        }
    }

    // 1.2 http://localhost:8081/orders/{id}
    @GetMapping("/orders/{id}")
    public String get(@PathVariable int id, @RequestParam(name="name", required=false, defaultValue="Xolisani") String name, Model model){
        Order order = orderService.getOrderById(id);

        if (null==order)
            return "error";
        else{
            model.addAttribute("orderId", order.getOrderId());
            model.addAttribute("amount", order.getAmount());
            model.addAttribute("VAT", order.getVAT());
            model.addAttribute("customerId", order.getCustomerId());

            return "order";
        }
    }

    // 1.3 http://localhost:8081/orders/customer/{customerId}
    @GetMapping("/orders/customer")
    public String getCustomerOrders(@RequestParam Integer customerid , Model model){
        List <Order> orders = orderService.getOrderByCustomerId(customerid);

        if(orders==null)
            return "error";

        else{
            model.addAttribute("orders", orders);
            return "orders";
        }
    }

    // 1.4 http://localhost:8081/orders/customers
    @GetMapping("/orders/customers")
    public String getOrdersAndCustomers(@RequestParam(name="name", required=false, defaultValue="User") String name, Model model){
        List<Integer> customerIds = orderService.getAllCustomersWithOrders() ;
        List<Order>[] arrayOfList = new List[customerIds.size()];
        Integer i = 0;
        for(Integer customerId : customerIds){
            List<Order> testOrders = orderService.getOrderByCustomerId(customerId);
            arrayOfList[i] = testOrders;
            i++;
        }
        if(customerIds.size()==0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else{
            model.addAttribute("customerIds", customerIds);
            model.addAttribute("arrayOfList", arrayOfList);
            return "customer-orders";
        }
    }

    // 1.5 http://localhost:8081/order
    @GetMapping("/order")
    public String orderHere(Model model){
        // get one order
        Order order = new Order();
        model.addAttribute("order", order);
        // get all orders
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "place";
    }

    // 2 http://localhost:8081/orders
    @PostMapping("/orders")
    public String create(@ModelAttribute("order") Order order){
        orderService.placeOrder( order, order.getCustomerId());
        return "ordered";
    }

    // 3. http://localhost:8081/orders/{id}
    @DeleteMapping("/orders/{id}")
    public void delete(@PathVariable Integer id){
        Order order = orderService.remove(id);
        if (order==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
