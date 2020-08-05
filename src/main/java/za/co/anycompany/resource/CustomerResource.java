package za.co.anycompany.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.service.CustomerService;
import za.co.anycompany.service.OrderService;

import java.util.List;

@RestController
@RequestMapping(value = "customer")
public class CustomerResource {

    private final OrderService orderService;
    private final CustomerService customerService;

    public CustomerResource(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @RequestMapping(value = "/{customerId}",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Customer> getCustomer(@PathVariable int customerId) {
        return ResponseEntity.ok(customerService.getCustomer(customerId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Customer> getCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @RequestMapping(value = "/{customerId}/place-order", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Boolean> getCustomer(@PathVariable int customerId, @RequestBody Order order) {
        return ResponseEntity.ok(orderService.placeOrder(order, customerId));
    }
}
