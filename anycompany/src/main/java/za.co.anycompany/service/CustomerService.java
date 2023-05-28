package za.co.anycompany.service;

import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.model.Customer;

public class CustomerService {

 private final CustomerRepository customerRepository;

  public CustomerService(){
    this.customerRepository = new CustomerRepository();
  }

  public void loadCustomersWithOrders(){
    customerRepository.loadCustomersWithOrders();
  }

  public void retrieveCustomerWithOrders(int customerId){
    customerRepository.retrieveCustomerWithOrders(customerId);
  }

  public void save(Customer customer) {
     customerRepository.save(customer);
  }


  public Customer find(int customerId) {
    return customerRepository.load(customerId);
  }



//  public int edit(Customer customer) {
//    return customerRepository.edit(customer);
//  }
//
//
//  public void delete(int customerId) {
//    customerRepository.delete(customerId);
//  }
//
//
//  public List<Customer> findAll() {
//    return customerRepository.findAll();
//  }
}
