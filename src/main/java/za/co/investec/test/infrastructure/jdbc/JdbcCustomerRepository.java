package za.co.investec.test.infrastructure.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import za.co.investec.test.domain.Customer;
import za.co.investec.test.domain.CustomerRepository;
import za.co.investec.test.domain.Order;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Component
@AllArgsConstructor
public class JdbcCustomerRepository implements CustomerRepository {

    private static final String CREATE_SQL = "Insert into Customers(name, country, date_of_birth) Values(?,?,?)";
    private static final String FIND_ALL_SQL = "Select *, o.id as order_id From Customers c left join Orders o on c.id = o.customer_id";
    private static final String FIND_BY_ID_SQL = "Select *, o.id as order_id From Customers c left join Orders o on c.id = o.customer_id Where c.id = ?";

    private final DataSource dataSource;

    @Override
    public Customer save(Customer customer) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_SQL, RETURN_GENERATED_KEYS)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getCountry());
            statement.setDate(3, Date.valueOf(customer.getDateOfBirth()));
            statement.executeUpdate();
            if (statement.getGeneratedKeys().next()) {
                customer.setId(statement.getGeneratedKeys().getLong(1));
            }
            return customer;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public List<Customer> findAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL)) {
            Map<Long, Customer> customers = new HashMap<>();
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    Customer customer = customers.get(id);
                    if (customer == null) {
                        customer = extractCustomer(resultSet);
                        customers.put(id, customer);
                    }
                    resultSet.getLong("order_id");
                    if (!resultSet.wasNull()) {
                        customer.getOrders().add(extractOrder(resultSet));
                    }
                }
            }
            return new ArrayList<>(customers.values());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Customer> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            Customer customer = null;
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (customer == null) {
                        customer = extractCustomer(resultSet);
                    }
                    resultSet.getLong("order_id");
                    if (!resultSet.wasNull()) {
                        customer.getOrders().add(extractOrder(resultSet));
                    }
                }
            }
            return Optional.ofNullable(customer);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private Order extractOrder(ResultSet resultSet) throws Exception {
        Order order = new Order();
        order.setId(resultSet.getLong("order_id"));
        order.setCustomerId(resultSet.getLong("customer_id"));
        order.setAmount(resultSet.getBigDecimal("amount"));
        order.setVat(resultSet.getBigDecimal("vat"));
        return order;
    }

    private Customer extractCustomer(ResultSet resultSet) throws Exception {
        Customer customer = new Customer();
        customer = new Customer();
        customer.setId(resultSet.getLong("id"));
        customer.setName(resultSet.getString("name"));
        customer.setCountry(resultSet.getString("country"));
        customer.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
        return customer;
    }
}
