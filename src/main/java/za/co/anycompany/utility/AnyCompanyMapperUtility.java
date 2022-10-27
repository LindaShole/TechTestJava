package za.co.anycompany.utility;

import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import za.co.anycompany.dto.CustomerDto;
import za.co.anycompany.dto.OrderDto;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

public class AnyCompanyMapperUtility {

	public static OrderDto orderDtoMapper(Order order) {
		OrderDto orderDto = new OrderDto();
		BeanUtils.copyProperties(order, orderDto);
		return orderDto;
	}

	public static CustomerDto customerDtoMapper(Customer consumer) {
		CustomerDto customerDto = new CustomerDto();
		BeanUtils.copyProperties(consumer, customerDto);

		/*
		 * Function<Order, OrderDto> innerMapper = (order) -> { OrderDto orderDto = new
		 * OrderDto(); BeanUtils.copyProperties(order, orderDto); return orderDto; };
		 */
		customerDto.setOrders(consumer.getOrders().stream().map(AnyCompanyMapperUtility::orderDtoMapper)
				.collect(Collectors.toList()));
		return customerDto;
	}

}