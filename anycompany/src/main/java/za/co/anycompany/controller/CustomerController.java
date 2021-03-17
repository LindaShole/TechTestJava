package za.co.anycompany.controller;

import java.util.ArrayList;
import java.util.List;
import za.co.anycompany.DTO.CustomerDTO;
import za.co.anycompany.model.Customer;
import za.co.anycompany.service.CustomerService;
import za.co.anycompany.service.OrderService;

public class CustomerController {

    private CustomerService customerService =  new CustomerService();
    private OrderService orderService =  new OrderService();

    public List<CustomerDTO> loadAll(){
        List<Customer> customers =  customerService.findAll();
        List<CustomerDTO> customerDTOS =  new ArrayList<>();
        for (Customer customer:customers) {
            CustomerDTO customerDTO =  new CustomerDTO(customer);
            customerDTO.setOrders(orderService.getAllForCustomer(customer.getId()));
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    public CustomerDTO loadCustomer(int customerId){
        CustomerDTO customerDTO =  new CustomerDTO(customerService.loadCustomer(customerId));
        customerDTO.setOrders(orderService.getAllForCustomer(customerId));
        return customerDTO;
    }

}
