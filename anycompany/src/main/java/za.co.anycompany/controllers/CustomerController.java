package za.co.anycompany.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.anycompany.model.Customer;
import za.co.anycompany.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping(value = "/customers",produces = "application/json")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService){
    this.customerService = customerService;
  }

  @GetMapping
  public ResponseEntity<List<Customer>> getAllCustomers(){

    List<Customer> customers = customerService.getAllCustomers();

    return ResponseEntity.ok(customers);
  }

  @GetMapping("/{Id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable("Id") Long customerId){

    Customer customer = customerService
            .getCustomer(customerId)
            .orElse(null);

    if(customer != null){
      return ResponseEntity
              .ok(customer);
    }
    else {
      return ResponseEntity
              .notFound()
              .build();
    }
  }

  @PutMapping("{Id}")
  private ResponseEntity<Customer> updateCustomer(@PathVariable("Id") Long Id,
                                                   @RequestBody Customer customer){

    Customer existingCustomer = customerService.getCustomer(Id).orElse(null);
    if(existingCustomer != null){
      customer.setCustomerId(Id);
      Customer updatedCustomer = customerService.updateCustomer(customer);
      return ResponseEntity.ok(updatedCustomer);

    }
    else {
      return ResponseEntity
              .notFound()
              .build();
    }
  }

  public ResponseEntity<Void> deleteCustomer(@PathVariable("Id") Long Id){
    Customer customer = customerService.getCustomer(Id).orElse(null);

    if(customer != null){

      customerService.deleteCustomer(Id);
      return ResponseEntity.noContent().build();

    }
    else {
      return ResponseEntity
              .notFound()
              .build();
    }

  }

}
