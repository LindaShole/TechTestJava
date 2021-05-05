package za.co.anycompany.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.anycompany.model.Customer;
import za.co.anycompany.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    public CustomerController()
    {

    }

    @GetMapping
    ResponseEntity loadCustomers(){
        return ResponseEntity.ok(customerService.getCustomers());
    }
    @GetMapping("/{id}")
    ResponseEntity loadCustomer(@PathVariable int id){
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @PostMapping
    ResponseEntity<Boolean> saveCustomer(@RequestBody Customer customer){
        boolean isCreated = customerService.addCustomer(customer);

        if(isCreated)
        {
            return ResponseEntity.created(null).build();
        }
        else
        {
            return ResponseEntity.badRequest().build();
        }
    }
}
