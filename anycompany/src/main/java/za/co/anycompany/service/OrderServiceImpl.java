package za.co.anycompany.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.persistance.dao.CustomerDao;
import za.co.anycompany.persistance.dao.OrderDao;
import za.co.anycompany.common.AnyCompanyConstants;

/**
 * This is where the order-related business logic and business rules are 
 * enforced/applied
 *
 * @author v-nchatitai
 */
@Service
public class OrderServiceImpl implements OrderService{

//    private final OrderRepository orderRepository = new OrderRepository();
    @Autowired
    CustomerDao customerDao;
    @Autowired
    OrderDao orderDao;

    /**
     * Throwback logic (with some updates) for the placement of an order
     * 
     * @param order
     * @param customerId
     * @return
     * @throws IOException 
     */
    @Override
    public boolean placeOrder(Order order, int customerId) throws IOException{
//        Customer customer = CustomerRepository.load(customerId);
        Customer customer = customerDao.getCustomerById(customerId);

        if (order.getAmount() == 0) {
            return false;
        }

        if (customer.getCountry().equals(AnyCompanyConstants.UK_VALUE)) {
            order.setVAT(0.2d);
        } else {
            order.setVAT(0);
        }

//        orderRepository.save(order);
        orderDao.insertNewOrder(order);

        return true;
    }

    @Override
    public List<Order> getOrdersByCustomerID(int customerId) throws IOException {
        
        return orderDao.getOrdersByCustomerId(customerId);
    }
}
