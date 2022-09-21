package za.co.anycompany.common;

/**
 * All hard-coded strings should be constants. Remember: all member values in an
 * interface are by default, static and final - the obvious reiterated here for 
 * readability. 
 *
 * @author v-nc
 */
public interface AnyCompanyConstants {

    public static final String MYBATIS_CONFIG_FILENAME = "mybatis-config.xml";
    public static final String UK_VALUE = "UK";
    public static final String ORDER_PLACEMENT_FAILURE_MSG = "Failed to save order. Contact support for assistance";
    public static final String ORDER_PLACEMENT_SUCCESS_MSG = "Order is placed successsfully";
    public static final String FETCH_ALL_CUSTOMERS_LOG_MSG = "Received request to fetch all customers";
    public static final String ERROR_FETCHING_ALL_CUSTOMERS_RESPONSE_MSG = "Error while fetching customers. Contact support for assistance";
    public static final String FETCH_CUSTOMER_BY_ID_MESSAGE = "Received request fetch customer by ID number";
    public static final String ERROR_FETCHING_CUSTOMER_BY_ID = "Error while fetching customer. Contact support for assistance ";
    public static final String FETCH_CUSTOMER_BY_ORDER_ID = "Received request to fetch a customer by historical order ID";
    public static final String ERROR_FETCHING_CUSTOMER_BY_ORDER_ID_LOG_MSG = "Error while fetch cutomer by order ID. Contact support for assistance ";
    public static final String RECEIVED_PLACE_ORDER_REQUEST_LOG_MSG = "Received request to place order";
    public static final String RECEIVED_GET_ORDERS_BY_CUSTOMER_ID_REQUEST_LOG_MSG = "Received request to fetch orders by customer ID";
    public static final String ERROR_FETCHING_ORDERS_BY_CUSTOMER_ID = "Error while fetching orders by customer ID. Contact support for assistance";
    public static final String ASTERISKS_CHAIN = "**********************************************";
    public static final String FOUND_THE_FOLLOWING_CUSTOMERS = "Found the following customer(s) in response to request for all";
    public static final String FOUND_THE_FOLLOWING_CUSTOMER_BY_ID = "Found the following customer in response to request for one by customer ID";
    public static final String FOUND_THE_FOLLOWING_CUSTOMER_BY_ORDER_ID = "Found the following customer in response to request for one by historical order ID";
    
}

