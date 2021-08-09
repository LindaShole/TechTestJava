package za.co.anycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.model.Customer;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(long customerId){
        return customerRepository.getById(customerId);
    }

    @Override
    public List<Customer> getCustomerList(){
        return customerRepository.findAll();
    }

    @Override
    public void saveCustomer(Customer customer){
        customerRepository.save(customer);
    }

}
