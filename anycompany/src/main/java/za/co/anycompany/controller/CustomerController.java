package za.co.anycompany.controller;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.anycompany.common.AnyCompanyConstants;
import za.co.anycompany.service.CustomerService;

/**
 *
 * @author v-nchatitai
 */
@RestController
@RequestMapping("customer")
public class CustomerController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    /**
     * Entry point for requests to get all customers
     * 
     * @return 
     */
    @RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
    public ResponseEntity<Object> getCustomers(){
        LOGGER.info(AnyCompanyConstants.FETCH_ALL_CUSTOMERS_LOG_MSG);
        try {
            return new ResponseEntity(customerService.getCustomers(), HttpStatus.OK);
        } catch (IOException ex) {
            return new ResponseEntity(AnyCompanyConstants.ERROR_FETCHING_ALL_CUSTOMERS_RESPONSE_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Entry point for HTTP request for a customer by given customer ID
     * 
     * @param id
     * @return 
     */
    @RequestMapping(value = "/getCustomer/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getCustomer(@PathVariable("id") String id){
        LOGGER.info(AnyCompanyConstants.FETCH_CUSTOMER_BY_ID_MESSAGE);
        try {
            return new ResponseEntity(customerService.getCustomer(Integer.parseInt(id)), HttpStatus.OK);
        } catch (IOException ex) {
            return new ResponseEntity(AnyCompanyConstants.ERROR_FETCHING_CUSTOMER_BY_ID, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Entry point for request for specific customer by historical order ID
     * 
     * @param orderId
     * @return 
     */
    @RequestMapping(value = "/customerByOrderId/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getCustomerByOrderId(@PathVariable("orderId") String orderId){
        LOGGER.info(AnyCompanyConstants.FETCH_CUSTOMER_BY_ORDER_ID);
        try {
            return new ResponseEntity(customerService.getCustomerByOrderId(Integer.parseInt(orderId)), HttpStatus.OK);
        } catch (IOException ex) {
            return new ResponseEntity(AnyCompanyConstants.ERROR_FETCHING_CUSTOMER_BY_ORDER_ID_LOG_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
