package za.co.anycompany.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import za.co.anycompany.dto.CustomerDto;
import za.co.anycompany.dto.CustomerOrdersDto;
import za.co.anycompany.dto.OrdersDto;
import za.co.anycompany.service.CustomerService;
import za.co.anycompany.service.OrderService;

@RestController
@RequestMapping(value = "/api")
public class Controller {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@Validated @RequestBody CustomerDto dto) {
        return customerService.createCustomer(dto);
    }

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@Validated @RequestBody OrdersDto dto) {
        orderService.placeOrder(dto);
    }

    @GetMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerOrdersDto findOrdersByCustomerId(@RequestParam(name = "customerId") Long id) {
        return orderService.findOrdersByCustomerId(id);
    }
}
