package za.co.anycompany.service;

import org.springframework.stereotype.Service;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.documents.CustomerOutput;
import za.co.anycompany.model.Customer;
import za.co.anycompany.validations.Screen;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CustomerService {

    private CustomerRepository customerRepository = new CustomerRepository();
    private OrderRepository orderRepository = new OrderRepository();

    private static final Logger logger = Logger.getLogger(String.valueOf(CustomerService.class));

    public boolean addCustomer(Customer customer)
    {
        if(!Screen.isValidString(customer.getName())||!Screen.isValidString(customer.getCountry()) || Screen.isValidDate(customer.getDateOfBirth()))
        {
            return customerRepository.save(customer);
        }

        return false;
    }

    public List<CustomerOutput> getCustomers()  {
        List<CustomerOutput> output = new ArrayList<>();

        try {

            List<Customer> customers = customerRepository.getCustomers();

            for (Customer customer: customers)
            {
                CustomerOutput tmpObj = new CustomerOutput();

                tmpObj.setCustomerId(customer.getCustomerId());
                tmpObj.setName(customer.getName());
                tmpObj.setCountry(customer.getCountry());
                tmpObj.setDateOfBirth(customer.getDateOfBirth());

                tmpObj.setOrders(orderRepository.findOrdersByCustomerId(customer.getCustomerId()));
                output.add(tmpObj);
            }

        } catch (SQLException e) {
            logger.log(Level.ALL,e.getMessage());
        }

        return output;
    }

    public CustomerOutput getCustomer(int customerId)
    {
        CustomerOutput output = new CustomerOutput();
        try {
            Customer customer = customerRepository.getCustomer(customerId);

            output.setCustomerId(customer.getCustomerId());
            output.setName(customer.getName());
            output.setCountry(customer.getCountry());
            output.setDateOfBirth(customer.getDateOfBirth());

            output.setOrders(orderRepository.findOrdersByCustomerId(customerId));

        } catch (SQLException e) {
            logger.log(Level.ALL,e.getMessage());
        }

        return output;
    }
}
