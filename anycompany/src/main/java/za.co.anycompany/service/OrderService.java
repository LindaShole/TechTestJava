package za.co.anycompany.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.anycompany.model.Order;
import za.co.anycompany.repository.CustomerRepository;
import za.co.anycompany.repository.OrderRepository;
import za.co.anycompany.model.Customer;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class OrderService {
    @Autowired
    CustomerRepository customerRepository;

    public void placeCustomerOrder(List<Customer> customerOrders){
        log.info("inside placeCustomerOrder() ");
        customerRepository.saveAll(customerOrders);
        log.info("Successful saved the customer details and their orders" + customerOrders.size());
    }
    public List<Customer> loadCustomerOrders(){
        log.info("inside loadCustomerOrders() ");
       return customerRepository.findAll();
    }


}
