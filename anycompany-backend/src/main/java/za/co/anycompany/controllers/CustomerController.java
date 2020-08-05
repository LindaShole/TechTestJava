package za.co.anycompany.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/{customerId}")
    ResponseEntity<Customer> loadCustomer(@PathVariable(value = "customerId") int customerId){
        return new ResponseEntity<>(customerService.load(customerId), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<Customer>> loadCustomers(){
        return new ResponseEntity<>(customerService.loadAllCustomers(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Boolean> saveCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.OK);
    }
}
