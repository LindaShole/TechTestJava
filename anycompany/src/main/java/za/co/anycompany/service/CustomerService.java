package za.co.anycompany.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.model.Customer;

@Component
public class CustomerService extends CustomerRepository{

    public Customer getCustomer(int customerId)
	{
		Customer result = CustomerRepository.load(customerId);
		
	    return result;
	}

    public List<Customer> getAllCustomers()
	{
		List<Customer> result = CustomerRepository.loadAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Customer>();
		}
	}

    public Customer createOrUpdateCustomer(Customer customer) 
	{
		if(customer.getCustomerId() == 0) 
		{
			customer = CustomerRepository.saveCustomer(customer);
			return customer;
		} 
        else{
            customer = CustomerRepository.editCustomer(customer);
			return customer;
        }
	}

    public void deleteCustomer(Integer customerId) 
	{
		CustomerRepository.deleteCustomer(customerId);
	}
    
}
