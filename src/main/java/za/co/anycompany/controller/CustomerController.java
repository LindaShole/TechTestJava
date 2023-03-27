package za.co.anycompany.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import za.co.anycompany.model.Customer;
import za.co.anycompany.service.CustomerService;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @Value("${spring.application.name}")
    String appName;
    // 1.0  http://localhost:8080/
    @GetMapping("/")
    public String getCustomersIndex(@RequestParam(name="name", required=false, defaultValue="User") String name, Model model) {
        List<Customer> customers = customerService.getAllCustomers();

        if(null==customers){
            return "error";
        }
        else {
            model.addAttribute("customers", customers);
            model.addAttribute("appName", appName);

            return "home";
        }
    }

    // 1.1  http://localhost:8080/
    @GetMapping("/home")
    public String getCustomersHome(@RequestParam(name="name", required=false, defaultValue="User") String name, Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("appName", appName);

        return "home";
    }

    // 1.2 http://localhost:8080/customer
    @GetMapping("/customer")
    public String getOneCustomers(@RequestParam Integer customerid, Model model) {
        Customer customer = customerService.getCustomerById(customerid);

        model.addAttribute("id", customer.getCustomerid());
        model.addAttribute("name", customer.getName());
        model.addAttribute("country", customer.getCountry());
        model.addAttribute("date_of_birth", customer.getDateOfBirth());
        model.addAttribute("appName", appName);

        return "customer";
    }

    // 1.3 http://localhost:8080/customers
    @GetMapping("/customers")
    public String getAllCustomers(@RequestParam(name="name", required=false, defaultValue="User") String name, Model model) {
        List<Customer> customers = customerService.getAllCustomers();

        model.addAttribute("name", name);
        model.addAttribute("customers", customers);
        model.addAttribute("appName", appName);

        return "customers";
    }

    // 1.4 http://localhost:8080/customers/{id}
    @GetMapping("/customers/{id}")
    private String getCustomer(@PathVariable int id, Model model){
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("name", customer.getName());
        model.addAttribute("country", customer.getCountry());
        model.addAttribute("date_of_birth", customer.getDateOfBirth());
        return "customer";
    }

    // 2. http://localhost:8080/customers/{id}
    @DeleteMapping("/customers/{id}")
    private void deleteCustomer(@PathVariable int id){
        customerService.delete(id);
    }

    // 3. http://localhost:8080/customers
    @PostMapping("/customers")
    private int saveCustomer(@RequestBody Customer customer){
        return  customer.getCustomerid();
    }

    // 4. http://localhost:8080/customers
    @PutMapping("/customers")
    private Customer update(@RequestBody Customer customer){
        customerService.saveOrUpdate(customer);
        return customer;
    }
}
