package za.co.anycompany.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import java.sql.Date;

import za.co.anycompany.datalayer.CustomerDAO;
import za.co.anycompany.datalayer.CustomerRepositoryImpl;
import za.co.anycompany.datalayer.DatabaseFactoryImpl;
import za.co.anycompany.datalayer.OrderDAO;
import za.co.anycompany.datalayer.OrderRepositoryImpl;
import za.co.anycompany.exception.CustomerDataException;
import za.co.anycompany.exception.InvalidCustomerException;
import za.co.anycompany.exception.InvalidOrderException;
import za.co.anycompany.exception.OrderException;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.service.OrderService;
import za.co.anycompany.service.OrderServiceImpl;

public class CustomerOrdersTest {
    CustomerDAO customerDAO = null;
    OrderDAO orderDAO = null;

    OrderService orderSvc = null;

    @Before
    public void setUp() {
        customerDAO = DatabaseFactoryImpl.getCustomerDAO();
        orderDAO = DatabaseFactoryImpl.getOrderDAO();

        orderSvc = new OrderServiceImpl(new OrderRepositoryImpl(), new CustomerRepositoryImpl());

        try {
            // Create customer and orders stores if does not exist
            customerDAO.setupCustomerSchema();
            orderDAO.setupOrderSchema();

            // In real world customerId and orderId would sequence or auto increment at database level
            customerDAO.create(new Customer(1, "John Smith", "ZA", Date.valueOf("1984-07-27")));
            customerDAO.create(new Customer(2, "Iggy Nkwinika", "ZA", Date.valueOf("1985-04-27")));
            customerDAO.create(new Customer(3, "Piet Gouws", "UK", Date.valueOf("1972-07-12")));
            customerDAO.create(new Customer(4, "Sizwe Els", "UK", Date.valueOf("1994-04-27")));
            customerDAO.create(new Customer(5, "Themba Zitha", "ZA", Date.valueOf("1978-03-31")));
        }
        catch(Exception e) {
        }
    }

    @Test
    public void testCustomerExist() throws CustomerDataException {
        Customer c = orderSvc.getCustomerOrders(3);

        assertEquals("Customer exist in data store", "Piet Gouws", c.getName());
    }

    @Test
    public void testOrderPlacement() throws OrderException, CustomerDataException, InvalidOrderException, InvalidCustomerException {
    
        boolean orderPlaced = orderSvc.placeOrder(new Order(1, 540.32), 1);
        Customer c = orderSvc.getCustomerOrders(1);

        assertEquals("Customer exist in data store", 1, c.getOrders().size());
    }

    @Test
    public void testOrderPlacementWithCorrectVAT() throws OrderException, CustomerDataException, InvalidOrderException, InvalidCustomerException {
    
        boolean orderPlaced = orderSvc.placeOrder(new Order(2, 298.21), 4);
        Customer c = orderSvc.getCustomerOrders(4);

        Order o = c.getOrders().get(0);

        assertEquals("Correct VAT was applied based on the country", 0.2, o.getVAT(), 0);
    }

    @Test(expected = InvalidCustomerException.class)
    public void testOrderPlacementByInvalidCustomer() throws OrderException, CustomerDataException, InvalidOrderException, InvalidCustomerException {
        boolean orderPlaced = orderSvc.placeOrder(new Order(3, 298.21), 100);

        assertEquals("Order placement throws invalid customer exception", false, orderPlaced);
    }

    @Test(expected = InvalidOrderException.class)
    public void testOrderPlacementWithInvalidAmount() throws OrderException, CustomerDataException, InvalidOrderException, InvalidCustomerException {
        boolean orderPlaced = orderSvc.placeOrder(new Order(4, 0.00), 1);

        assertEquals("Order placement throws invalid order exception", false, orderPlaced);
    }    
}
