package co.za.anycompany.service;

import co.za.anycompany.model.Customer;

public interface CustomerService {
	Customer createCustomer(Customer customer) throws Exception;
	Customer findCustomerByCutsomerId(String customerId) throws Exception;
	
}
