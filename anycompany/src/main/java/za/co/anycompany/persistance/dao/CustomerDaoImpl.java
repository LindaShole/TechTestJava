package za.co.anycompany.persistance.dao;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.co.anycompany.model.Customer;
import za.co.anycompany.persistence.mapper.CustomerMapper;
import za.co.anycompany.common.AnyCompanyConstants;

/**
 *
 * @author v-nchatitai
 */
@Component
public class CustomerDaoImpl implements CustomerDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDaoImpl.class);

    @Override
    public List<Customer> getCustomers() throws IOException {
        List<Customer> customers = null;
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(AnyCompanyConstants.MYBATIS_CONFIG_FILENAME));
        try ( SqlSession session = sqlSessionFactory.openSession()) {
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            customers = mapper.getCustomers();
            LOGGER.info(AnyCompanyConstants.ASTERISKS_CHAIN);
            LOGGER.info(AnyCompanyConstants.FOUND_THE_FOLLOWING_CUSTOMERS);
            LOGGER.info(Arrays.toString(customers.toArray()));
        }

        return customers;
    }

    @Override
    public Customer getCustomerById(int customerId) throws IOException {
        Customer customer = null;
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(AnyCompanyConstants.MYBATIS_CONFIG_FILENAME));
        try ( SqlSession session = sqlSessionFactory.openSession()) {
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            customer = mapper.getCustomerById(customerId);
            LOGGER.info(AnyCompanyConstants.ASTERISKS_CHAIN);
            LOGGER.info(AnyCompanyConstants.FOUND_THE_FOLLOWING_CUSTOMER_BY_ID);
            LOGGER.info(customer.toString());
        }

        return customer;
    }

    @Override
    public Customer getCustomerByOrderId(int orderId) throws IOException {
        Customer customer = null;
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(AnyCompanyConstants.MYBATIS_CONFIG_FILENAME));
        try ( SqlSession session = sqlSessionFactory.openSession()) {
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            customer = mapper.getCustomerByOrderId(orderId);
            LOGGER.info(AnyCompanyConstants.ASTERISKS_CHAIN);
            LOGGER.info(AnyCompanyConstants.FOUND_THE_FOLLOWING_CUSTOMER_BY_ORDER_ID);
            LOGGER.info(customer.toString());
        }

        return customer;
    }
}
