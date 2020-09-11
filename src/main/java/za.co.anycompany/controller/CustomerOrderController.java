package za.co.anycompany.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import za.co.anycompany.dto.CustomerDTO;
import za.co.anycompany.service.OrderService;
import za.co.anycompany.model.Order;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/anyCompany")
public class CustomerOrderController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerOrderController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<CustomerDTO> getAllCustomers(){
        LOG.info("Getting all customers with orders");

        List<CustomerDTO>  customerDTOList = new ArrayList<>();
        customerDTOList.addAll(orderService.loadCustomers());

        LOG.info("customers numbers "+customerDTOList.size());

        return customerDTOList;
    }

    @PutMapping("/placeOrder/{customerId}")
    public @ResponseBody
    boolean placeOrder(@RequestBody Order order, @PathVariable int customerId){
        LOG.info(String.format("Order : %s and customerId: %s ", order, customerId));

        boolean isSave = orderService.placeOrder(order, customerId);

        LOG.info("save status : "+isSave);

        return isSave;
    }

   @GetMapping("/getCustomer/{customerId}")
    public @ResponseBody
    CustomerDTO getCustomer(@PathVariable int customerId){
        LOG.info("customerId : "+customerId);

        CustomerDTO  customerDTO = orderService.getCustomer(customerId);

        LOG.info("customer info : "+customerDTO);
        return customerDTO;
    }
}
