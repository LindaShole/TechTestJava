package za.co.anycompany.service;

import za.co.anycompany.common.DatabaseManager;
import za.co.anycompany.common.exception.NullConnectionException;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * <p>Title: OrderService</p>
 *
 * <p>Description:  Service that provide functionality to persist customers orders</p>
 *
 * <p>Company: AnyCompany</p>
 *
 * @author Chizeba Maulu
 */

public class OrderService {
    public static final double VAT = 0.2d;
    private static final Logger LOG = Logger.getLogger(OrderService.class.getName());

    public OrderService() {
        DatabaseManager.getInstance().createOrderTableIfNotExist();
        DatabaseManager.getInstance().createCustomerTableIfNotExist();
    }

    /**
     * Place order for the respective customer
     *
     * @param order      the order
     * @param customerId the customer id
     * @return true if order is successfully place, false otherwise
     **/
    public boolean placeOrder(Order order, int customerId) {
        Customer customer;
        try {
            customer = CustomerRepository.findCustomerById(customerId);
        }
        catch (NullConnectionException | SQLException e) {
            LOG.warning("Could not place order " + order.getOrderId() + " for customer with id " + customerId + "\n" + e.getMessage());
            return false;
        }

        if (order.getAmount() == 0) {
            return false;
        }

        if (Objects.equals(customer.getCountry(), "UK")) {
            order.setVat(VAT);
        }
        else {
            order.setVat(0);
        }

        OrderRepository orderRepository = new OrderRepository();
        try {
            orderRepository.save(order, customerId);
        }
        catch (NullConnectionException | SQLException e) {
            LOG.warning("Could not save order " + order.getOrderId() + " for customer with id " + customerId + "\n" + e.getMessage());
            return false;
        }
        return true;
    }
}
