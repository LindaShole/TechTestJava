package za.co.anycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.anycompany.model.Customer;
import za.co.anycompany.service.OrderService;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @ResponseStatus
    @PostMapping( path = "placeCustomerOrder/service/v1", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> placeCustomerOrder(@RequestBody List<Customer> placeOrderRequests) {
        orderService.placeCustomerOrder(placeOrderRequests);
    return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus
    @GetMapping(path = "retrieveCustomerOrders/service/v1" )
    public List<Customer> retrieveAllCustomer() {
        return orderService.loadCustomerOrders();
    }


}
