package za.co.anycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import za.co.anycompany.model.Customer;
import za.co.anycompany.service.CustomerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Validated
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/order")
    public ResponseEntity<List<Customer>> getCustomers(@RequestParam(required = false) String name,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "3") int size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Customer> customers = customerService.findAllCustomers(name, paging);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(customers.getContent(), HttpStatus.OK);
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
        Customer customer = customerService.findCustomer(id);
        return  new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<List<Customer>> addCustomer(@Valid @RequestBody List<Customer> customers) {

        List<Customer> customerList = customerService.addCustomer(customers);
        return new ResponseEntity<>(customerList, HttpStatus.CREATED);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Customer> updateCustomer(@Valid @PathVariable("id") long id, @RequestBody Customer customer) {

        Customer customerEntity = customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(customerEntity, HttpStatus.OK);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") long id) {

        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/order")
    public ResponseEntity<HttpStatus> deleteAllCustomers() {

        customerService.deleteAllCustomers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}