package za.co.anycompany.service;

import za.co.anycompany.datalayer.CustomerOrderRepository;
import za.co.anycompany.model.CustomerOrder;

import java.util.List;

public class CustomerService {

    private CustomerOrderRepository customerOrderRepository;

    public CustomerService(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    public List<Customer> getAll(){
        return customerOrderRepository.getAll();
    }
}