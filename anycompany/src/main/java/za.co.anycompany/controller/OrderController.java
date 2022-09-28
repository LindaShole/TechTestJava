package za.co.anycompany.datalayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

@RestController
public class OrderController{

    @Autowired
    OrderService orderService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/getAllOrders", method = RequestMethod.GET)
    private List<Order> getOrders()
    {
        return orderService.getAllOrders();
    }

    /**
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    private Order getOrders(@PathVariable("orderId") int orderId)
    {
        return orderService.getOrderById(orderId);
    }

    /**
     *
     * @param orders
     * @return
     */
    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    private int saveOrder(@RequestBody Order orders)
    {
        orderService.saveOrUpdate(orders);
        return books.getOrderId();
    }

    /**
     *
     * @param orders
     * @return
     */
    @RequestMapping(value = "/orders", method = RequestMethod.PUT)
    private Order update(@RequestBody Order orders)
    {
        orderService.saveOrUpdate(orders);
        return orders;
    }

}