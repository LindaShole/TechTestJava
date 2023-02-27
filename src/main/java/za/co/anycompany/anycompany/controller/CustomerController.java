package za.co.anycompany.anycompany.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import za.co.anycompany.anycompany.model.Customer;
import za.co.anycompany.anycompany.service.CustomerService;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    // Test
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/customers/home")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    // http://localhost:8080/customers
    @GetMapping("/customers") // customers with orders

    private List<Customer> getAllCustomers() throws Exception{
        //List<Customer>
         return customerService.getAllCustomers();
     //   return "customers"; //
     //      return "home";
    }

    //http://localhost:8080/customers/{id}
    @GetMapping("/customers/{id}")
    private String getCustomer(@PathVariable int id, @RequestParam(name="name", required=false, defaultValue="Xolisani") String name, Model model){

        //model.addAttribute("name", name);
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("name", customer.getName());
        model.addAttribute("country", customer.getCountry());
        return "customer"; // return form
    }

    //http://localhost:8080/customers/test/{id}
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
}
