package za.co.anycompany.service.Impl;

import org.springframework.stereotype.Service;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.service.CustomerService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository){
    this.customerRepository = customerRepository;
  }

  @Override
  public void addCustomer(Customer customer){
    customerRepository.save(customer);
  }

  @Override
  public void updateCustomer(Customer customer){
    customerRepository.save(customer);
  }

  @Override
  public void deleteCustomer(Customer customer){
    customerRepository.delete(customer);
  }

  @Override
  public void deleteCustomer(int customerId){
    customerRepository.findById(customerId);
  }

  @Override
  public List<Customer> getAllCustomers(){
    return (List<Customer>) customerRepository.findAll();
  }

  @Override
  public Optional<Customer> getCustomer(int customerId){
    return customerRepository.findById(customerId);
  }
}
