package za.co.anycompany.anycompany.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import za.co.anycompany.anycompany.model.Customer;
import za.co.anycompany.anycompany.service.CustomerService;

import java.sql.ResultSet;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    // http://localhost:8080/customers
    @GetMapping("/customers")
    private List<Customer> getAllCustomers() throws Exception{
        ResultSet resultsSet=null;
        return customerService.getAllCustomers();
     //   return "customers"; // return form
    }

    //http://localhost:8080/customers/{id}
    @GetMapping("/customers/{id}")
    private Customer getCustomer(@PathVariable int id){
        // Customer
        return customerService.getCustomerById(id);
       // return "customer.html"; // return form
    }

    //http://localhost:8080/customers/{id}
    @GetMapping("/customers/test/{id}")
    private Customer getCustomerTest(@PathVariable int id){
        return customerService.getCustomerById(id);
    }


    //http://localhost:8080/customers/{id}
    @DeleteMapping("/customers/{id}")
    private void deleteCustomer(@PathVariable int id){
        customerService.delete(id);
    }

    // http://localhost:8080/customers
    @PostMapping("/customers")
    private int saveCustomer(@RequestBody Customer customer){
        return  customer.getCustomerid();
    }

    // http://localhost:8080/customers
    @PutMapping("/customers")
    private Customer update(@RequestBody Customer customer){
        customerService.saveOrUpdate(customer);
        return customer;
    }

/*
    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable int id) throws Exception{
        return customerService.findById(id);
    }

    @GetMapping("/customer/{id}")
    public Customer get(@PathVariable int id){
        Customer customer = customerService.get(id);
        if (order==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return order;
    } */
}
