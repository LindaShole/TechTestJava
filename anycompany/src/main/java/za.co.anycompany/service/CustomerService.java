package za.co.anycompany.service;

import org.springframework.stereotype.Service;
import za.co.anycompany.model.Customer;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {
  void addCustomer(final Customer customer);
  Customer updateCustomer(final Customer customer);
  void deleteCustomer(final Customer customer);
  void deleteCustomer(final Long customerId);
  List<Customer> getAllCustomers();
  Optional<Customer> getCustomer(final Long customerId);
}
