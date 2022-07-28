package za.co.anycompany.service;

import java.util.List;

import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.CustomerRepositoryImpl;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.exception.CustomerDataException;
import za.co.anycompany.exception.InvalidCustomerException;
import za.co.anycompany.exception.InvalidOrderException;
import za.co.anycompany.exception.OrderException;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public boolean placeOrder(Order order, int customerId) throws OrderException, CustomerDataException, InvalidOrderException, InvalidCustomerException
    {
        try {
            // direct reference because of project constraints on static declarations
            Customer customer = CustomerRepositoryImpl.load(customerId);

            if((customer == null) || (customer.getName() == null))
                throw new InvalidCustomerException(String.format("Customer %s does not exist.", customerId));

            if (order.getAmount() <= 0)
                throw new InvalidOrderException("Bad request, order amount cannot be less than or equal to zero");

            order.setVAT(Double.parseDouble(ConfigServiceImpl.getConfigurationItem(ConfigTypeEnum.Vat, customer.getCountry())));

            orderRepository.add(order, customer);

            return true;
        }
        catch(OrderException | CustomerDataException | InvalidOrderException e) {
            // Log exception before rethrowing
            throw e;
        }
        catch(Exception e) {
            throw e;
        }
    }

    @Override
    public List<Customer> listCustomersOrders() throws CustomerDataException {
        try{
            return customerRepository.getAllCustomersWithOrders();
        }
        catch(CustomerDataException e) {
            // Log exception before rethrowing
            throw e;
        }
        catch(Exception e) {
            // Log exception before rethrowing
            throw e;
        }
    }

    @Override
    public Customer getCustomerOrders(int customerId) throws CustomerDataException {
        try{
            return customerRepository.getCustomerWithOrders(customerId);
        }
        catch(CustomerDataException e) {
            // Log exception before rethrowing
            throw e;
        }
        catch(Exception e) {
            // Log exception before rethrowing
            throw e;
        }
    }
}
