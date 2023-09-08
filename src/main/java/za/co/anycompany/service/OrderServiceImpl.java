package za.co.anycompany.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import za.co.anycompany.dto.NewOrderDTO;
import za.co.anycompany.dto.OrderDTO;
import za.co.anycompany.mapper.OrderMapper;
import za.co.anycompany.persistence.entity.OrderEntity;
import za.co.anycompany.persistence.repository.OrderRepository;

import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final OrderMapper orderMapper;
    private final VatCalculator vatCalculator;

    @Override
    public UUID placeOrder(NewOrderDTO order) {
        var customer = customerService.getCustomerEntity(order.customerId());
        if (customer.isEmpty())
            throw new IllegalArgumentException(String.format("Customer id: %s is invalid.", order.customerId()));

        var customerEntity = customer.get();

        var orderEntity = new OrderEntity();
        orderEntity.setAmount(order.amount());
        orderEntity.setCustomer(customerEntity);
        orderEntity.setVat(vatCalculator.calculate(customerEntity.getCountry()));

        try {
            return orderRepository.save(orderEntity).getId();
        } catch (Exception e) {
            log.error("Unexpected error while creating order for {}", kv("customerId", order.customerId()));
            throw e;
        }
    }

    @Override
    public OrderDTO getOrder(UUID id) {
        try {
            return orderRepository.findById(id)
                    .map(orderMapper::toDTO)
                    .orElse(null);
        } catch (Exception e) {
            log.error("Unexpected error while retrieving details for {}", kv("orderId", id), e);
            throw e;
        }
    }
}