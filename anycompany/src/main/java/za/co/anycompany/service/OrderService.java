package za.co.anycompany.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order; 

@Component
public class OrderService extends OrderRepository{

    private OrderRepository orderRepository = new OrderRepository();

    public boolean placeOrder(Order order, int customerId)
    {
        Customer customer = CustomerRepository.load(customerId);

        if (order.getAmount() == 0)
            return false;

        if (customer.getCountry() == "UK")
            order.setVAT(0.2d);
        else
            order.setVAT(0); 

        orderRepository.save(order);

        return true; 
    }

    public List<Order> getAllOrders()
	{
		List<Order> result = OrderRepository.loadAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Order>();
		}
	}

    public List<Order> getCustomerOrders(Integer customerId)
	{
		List<Order> result = OrderRepository.customerOrders(customerId);
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Order>();
		}
	}

    public Order getOrder(int orderId)
	{
		Order result = OrderRepository.load(orderId);
		
	    return result;
	}

    public void deleteOrder(Integer orderId) 
	{
		OrderRepository.deleteOrder(orderId);
	}
} 
