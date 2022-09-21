package za.co.anycompany.persistance.dao;

import java.io.IOException;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.co.anycompany.model.Order;
import za.co.anycompany.persistence.mapper.OrderMapper;
import za.co.anycompany.common.AnyCompanyConstants;

/**
 * Manages DB interactions with the order table
 * 
 * @author v-nchatitai
 */
@Component
public class OrderDaoImpl implements OrderDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoImpl.class);

    /**
     * Insert a new order in the DB. 
     * 
     * @param order
     * @throws IOException 
     */
    @Override
    public void insertNewOrder(Order order) throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(AnyCompanyConstants.MYBATIS_CONFIG_FILENAME));
        try ( SqlSession session = sqlSessionFactory.openSession()) {
            OrderMapper mapper = session.getMapper(OrderMapper.class);
            mapper.save(order);
        }
    }

    /**
     * Fetch all orders from the database
     * 
     * @return
     * @throws IOException 
     */
    @Override
    public List<Order> getOrders() throws IOException {
        List<Order> orders = null;
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(AnyCompanyConstants.MYBATIS_CONFIG_FILENAME));
        try ( SqlSession session = sqlSessionFactory.openSession()) {
            OrderMapper mapper = session.getMapper(OrderMapper.class);
            orders = mapper.getOrders();
        }

        return orders;
    }

    /**
     * For a given customer ID number: Fetch all their orders
     * 
     * @param customerId
     * @return
     * @throws IOException 
     */
    @Override
    public List<Order> getOrdersByCustomerId(int customerId) throws IOException {
        List<Order> orders = null;
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(AnyCompanyConstants.MYBATIS_CONFIG_FILENAME));
        try ( SqlSession session = sqlSessionFactory.openSession()) {
            OrderMapper mapper = session.getMapper(OrderMapper.class);
            orders = mapper.getOrdersByCustomerId(customerId);
        }

        return orders;
    }
}
