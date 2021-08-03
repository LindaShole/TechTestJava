package za.co.anycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.anycompany.controller.ApplicationException;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.domain.Customer;
import za.co.anycompany.domain.Order;
import za.co.anycompany.dto.CustomerOrdersDto;
import za.co.anycompany.dto.OrderDto;
import za.co.anycompany.dto.OrdersDto;
import za.co.anycompany.translator.Translator;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    public boolean placeOrder(OrdersDto dto)
    {
        Customer customer = customerRepository.findOne(dto.getCustomerId());
        if (customer == null){
            throw new ApplicationException(ApplicationException.Type.entity_not_found, ApplicationException.CODE_CUSTOMER_NOT_FOUND, "Customer not found");
        }

        dto.getOrders().forEach(orderDto -> {
            if (orderDto.getAmount() == 0)
                throw new ApplicationException(ApplicationException.Type.amount_error, ApplicationException.CODE_ORDER_AMOUNT_NOT_ALLOWED, "R0 amount not allowed");

            if (customer.getCountry() == "UK")
                orderDto.setVat(0.2d);
            else
                orderDto.setVat(0);

            try {
                orderRepository.save(Translator.toOrder(orderDto, customer));
            } catch (Exception e){
                throw new ApplicationException(ApplicationException.Type.unexpected, ApplicationException.CODE_UNSPECIFIED, "Error occured while saving saving customer");
            }
        });

       return true;
    }

    public CustomerOrdersDto findOrdersByCustomerId(Long customerId) {
        List<OrderDto> orders = new ArrayList<>();

        Customer customer = customerService.findCustomerById(customerId);
        orderRepository.findByCustomer(customer).forEach(order -> {
            orders.add(Translator.orderDto(order));
        });
        CustomerOrdersDto dto = new CustomerOrdersDto();
        dto.setCustomer(Translator.toCustomerDto(customer));
        dto.setOrders(orders);
        return dto;
    }

    public OrderDto retrieveOrderById(Long id) {
        return Translator.orderDto(orderRepository.findOne(id));
    }

    public OrdersDto findAllOrders(){
        List<OrderDto> list = new ArrayList<>();
        orderRepository.findAll().forEach(order -> {
            list.add(Translator.orderDto(order));
        });
        OrdersDto li = new OrdersDto();
        li.setOrders(list);
        return li;
    }
}
