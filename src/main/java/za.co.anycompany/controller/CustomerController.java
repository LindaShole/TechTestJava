package za.co.anycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.anycompany.model.Customer;
import za.co.anycompany.service.CustomerService;

import java.util.List;

@RestController(value = "customer")
@RequestMapping(value = "customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public List<Customer> getCustomers() {

        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable String id) {

        Long idLong = Long.parseLong(id);

        return customerService.getCustomerById(idLong);
    }

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {

        return customerService.createCustomer(customer);
    }
}
