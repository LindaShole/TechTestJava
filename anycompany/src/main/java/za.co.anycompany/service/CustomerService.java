package service;

import java.util.List;

import datalayer.CustomerRepository;
import model.Customer;

public class CustomerService {
	
    public Customer getCustomer(int customerId){
        return CustomerRepository.loadWithOrders(customerId);
     }

     public List<Customer> getCustomerList(){
         return CustomerRepository.loadAll();
     }
     
     public void save(Customer customer){
    	 CustomerRepository.save(customer);
     }
    

}
