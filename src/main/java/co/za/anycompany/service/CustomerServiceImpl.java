package co.za.anycompany.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.za.anycompany.model.Customer;
import co.za.anycompany.repository.CustomersRepository;

/**
 * @author SiBhengu Provides data persistence to the db.
 */

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomersRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) throws Exception {

		Customer createCustomer = new Customer();
		
		createCustomer.setCutsomerId(UUID.randomUUID().toString());
		createCustomer.setName(customer.getName());
		createCustomer.setCountry(customer.getCountry());
		createCustomer.setDateOfBirth(customer.getDateOfBirth());
	
		customerRepository.save(createCustomer);

        return createCustomer; 
	}
	
	@Override
	public Customer findCustomerByCutsomerId(String customerId) throws Exception {
		 return customerRepository.findCustomerByCutsomerId(customerId); 
	}
	
}
