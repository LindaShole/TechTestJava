package za.co.anycompany.service;

import java.util.List;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.model.Customer;

public class CustomerService {

    private CustomerRepository customerRepository = new CustomerRepository();

    public List<Customer> findAll(){
        return customerRepository.loadAll();
    }


    public Customer loadCustomer(int  customerId){
        return customerRepository.load(customerId);
    }




}
