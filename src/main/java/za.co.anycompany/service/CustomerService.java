package za.co.anycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.model.Customer;

import java.util.List;

@Service
public  class CustomerService  {

    @Autowired
    CustomerRepository customerRepository;

    public Customer customer(long customerId){
        return customerRepository.getOne(customerId);
    }

    public List<Customer> customerList(){
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

}
