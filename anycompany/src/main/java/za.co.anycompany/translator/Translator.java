package za.co.anycompany.translator;

import za.co.anycompany.domain.Customer;
import za.co.anycompany.domain.Order;
import za.co.anycompany.dto.CustomerDto;
import za.co.anycompany.dto.OrderDto;

public class Translator {
    public static Customer toCustomer(CustomerDto dto){
        Customer customer = new Customer();
        customer.setCountry(dto.getCountry());
        customer.setDateOfBirth(dto.getDateOfBirth());
        customer.setIdNo(dto.getIdNo());
        customer.setName(dto.getName());
        return customer;
    }

    public static CustomerDto toCustomerDto(Customer customer){
        CustomerDto dto = new CustomerDto();
        dto.setCountry(customer.getCountry());
        dto.setDateOfBirth(customer.getDateOfBirth());
        dto.setIdNo(customer.getIdNo());
        dto.setName(customer.getName());
        return dto;
    }

    public static Order toOrder(OrderDto dto, Customer customer){
        Order order = new Order();
        order.setAmount(dto.getAmount());
        order.setVat(dto.getVat());
        order.setCustomer(customer);
        return order;
    }

    public static OrderDto orderDto(Order order){
        OrderDto dto = new OrderDto();
        dto.setAmount(order.getAmount());
        dto.setVat(order.getVat());
        dto.setId(order.getId());
        return dto;
    }
}
