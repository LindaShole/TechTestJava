package za.co.anycompany.service;

import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();
	private CustomerOrderRepository  customerOrderRepository = new CustomerOrderRepository();

    public boolean placeOrder(Order order, int customerId)
    {
        Customer customer = CustomerRepository.load(customerId);

        if (order.getAmount() == 0)
            return false;

        if (customer.getCountry() == "UK")
            order.setVAT(0.2d);
        else
            order.setVAT(0);

		order.setCustomer(customer);
        orderRepository.save(order);

        return true;
    }
	
	public CustomerOrder getCustomerOrder(int customerId){	
		return customerOrderRepository.load(customerId);  
	}
	
	public List<CustomerOrder> getCustomerOrder(){		
		return customerOrderRepository.loadAll();  
	}	
}
