package za.co.anycompany.datalayer;

import za.co.anycompany.exception.OrderException;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

public class OrderRepositoryImpl implements OrderRepository {
    
    private final OrderDAO orderDAO;
    
    public OrderRepositoryImpl() {
        orderDAO = DatabaseFactoryImpl.getOrderDAO();
    }
    
    @Override
    public void add(Order order, Customer customer) throws OrderException {
        try {
            orderDAO.create(order, customer);
        }
        catch(Exception e) {
            throw new OrderException(e.getMessage());
        }
    }
}
