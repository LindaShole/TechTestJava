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

import java.util.ArrayList;
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

    // 1.  http://localhost:8080/home
    @GetMapping("/home")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="User") String name, Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        List<Integer> ids = new ArrayList<>();
        for(Customer customer : customers) {
            model.addAttribute("id", customer.getId());
            model.addAttribute("name", customer.getName());
            model.addAttribute("country", customer.getCountry());
            ids.add(customer.getId());
        }
       // Customer customer = customers.get(0);
        model.addAttribute("ids", ids);
        model.addAttribute("appName", appName);

        return "index";
    }

    /* // http://localhost:8080/customers
    @GetMapping("/customers") // customers with orders

    private String getAllCustomers() throws Exception{
        //List<Customer>
        List<Customer> customers = customerService.getAllCustomers();
        return "customers"; //
      //     return "index";
    }*/

    // 2. http://localhost:8080/customers/{id}
    @GetMapping("/customers/{id}")
    private String getCustomer(@PathVariable int id, @RequestParam(name="name", required=false, defaultValue="Xolisani") String name, Model model){
        //model.addAttribute("name", name);
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("name", customer.getName());
        model.addAttribute("country", customer.getCountry());
        model.addAttribute("date_of_birth", customer.getDateOfBirth());
        return "customer"; // return form
    }

    // 3. http://localhost:8080/customers/test/{id}
    @GetMapping("/customers/test/{id}")
    private Customer getCustomerTest(@PathVariable int id){
        return customerService.getCustomerById(id);
    }


    // 4. http://localhost:8080/customers/{id}
    @DeleteMapping("/customers/{id}")
    private void deleteCustomer(@PathVariable int id){
        customerService.delete(id);
    }

    // 5. http://localhost:8080/customers
    @PostMapping("/customers")
    private int saveCustomer(@RequestBody Customer customer){
        return  customer.getCustomerid();
    }

    // 6. http://localhost:8080/customers
    @PutMapping("/customers")
    private Customer update(@RequestBody Customer customer){
        customerService.saveOrUpdate(customer);
        return customer;
    }
}
