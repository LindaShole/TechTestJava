package za.co.investec.test.infrastructure.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import za.co.investec.test.domain.Order;
import za.co.investec.test.domain.OrderRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Component
@AllArgsConstructor
public class JdbcOrderRepository implements OrderRepository {

    private static final String CREATE_SQL = "Insert into Orders(customer_id, amount, vat) Values(?,?,?)";
    private static final String FIND_ALL_SQL = "Select * From Orders";
    private static final String FIND_BY_ID_SQL = "Select * From Orders Where id = ?";

    private final DataSource dataSource;

    @Override
    public Order save(Order order) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_SQL, RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getCustomerId());
            statement.setBigDecimal(2, order.getAmount());
            statement.setBigDecimal(3, order.getVat());
            statement.executeUpdate();
            if (statement.getGeneratedKeys().next()) {
                order.setId(statement.getGeneratedKeys().getLong(1));
            }
            return order;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public List<Order> findAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Order> result = new ArrayList<>();
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(extractOrder(resultSet));
                }
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Order> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(extractOrder(resultSet));
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private Order extractOrder(ResultSet resultSet) throws Exception {
        Order order = new Order();
        order.setId(resultSet.getLong("id"));
        order.setCustomerId(resultSet.getLong("customer_id"));
        order.setAmount(resultSet.getBigDecimal("amount"));
        order.setVat(resultSet.getBigDecimal("vat"));
        return order;
    }
}
