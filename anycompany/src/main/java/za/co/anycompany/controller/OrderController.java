package za.co.anycompany.controller;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.anycompany.model.Order;
import za.co.anycompany.service.OrderService;
import za.co.anycompany.common.AnyCompanyConstants;

/**
 * Separates the business logic from the task of connecting to the rest of the
 * world
 *
 * @author v-nchatitai
 */
@RestController
@RequestMapping("order")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    /**
     * Receives client request to place an order and returns an appropriate HTTP
     * response indicating success or failure of the operation
     *
     * @param id
     * @param order
     * @return
     */
    @RequestMapping(value = "/place-order/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> placeOrder(@PathVariable("id") String id, @RequestBody Order order) {
        LOGGER.info(AnyCompanyConstants.RECEIVED_PLACE_ORDER_REQUEST_LOG_MSG);
        try {
            orderService.placeOrder(order, Integer.parseInt(id));
        } catch (IOException ex) {
            return new ResponseEntity<>(AnyCompanyConstants.ORDER_PLACEMENT_FAILURE_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(AnyCompanyConstants.ORDER_PLACEMENT_SUCCESS_MSG, HttpStatus.OK);
    }

    @RequestMapping(value = "/ordersByCustomerId/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getOrdersByCustomerId(@PathVariable("id") String id) {
        LOGGER.info(AnyCompanyConstants.RECEIVED_GET_ORDERS_BY_CUSTOMER_ID_REQUEST_LOG_MSG);
        try {
            return new ResponseEntity(orderService.getOrdersByCustomerID(Integer.parseInt(id)), HttpStatus.OK);
        } catch (IOException ex) {
            return new ResponseEntity(AnyCompanyConstants.ERROR_FETCHING_ORDERS_BY_CUSTOMER_ID, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
