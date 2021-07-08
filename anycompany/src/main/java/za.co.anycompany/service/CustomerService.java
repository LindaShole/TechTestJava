package za.co.anycompany.service;

import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.dtos.CustomerDTO;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.mappers.OrderMapper;
import za.co.anycompany.mappers.CustomerMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {

    private OrderRepository orderRepository = new OrderRepository();
    private CustomerMapper customerMapper = new CustomerMapper();
    private OrderMapper orderMapper = new OrderMapper();

    public CustomerDTO getCustomer(int customerId)
    {
        Customer customer = CustomerRepository.load(customerId);
        CustomerDTO customerDTO = customerMapper.fromEntityToDTO(customer);
        List<Order> orders = orderRepository.load(customerId);
        customerDTO.setOrders(orderMapper.fromEntitiesToDTOs(orders));

        return customerDTO;
    }

    public List<CustomerDTO> getAllCustomers()
    {
        List<Customer> customers = CustomerRepository.loadAll();
        List<CustomerDTO> customerDTOs = customerMapper.fromEntitiesToDTOs(customers);

        List<CustomerDTO> newCustomerDTOs = customerDTOs.stream()
                                            .map(r-> {
                                                List<Order> orders = orderRepository.load(r.getId());
                                                r.setOrders(orderMapper.fromEntitiesToDTOs(orders));
                                                return r;
                                            }).collect(Collectors.toList());

        return newCustomerDTOs;
    }
}
