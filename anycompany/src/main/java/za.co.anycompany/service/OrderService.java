package za.co.anycompany.service;

import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository

    public List<Order> getAllBy(String id, List<String> name) {
    return orderRepository.queryBy(id, name);
    }

    public void insertWithQuery(Customer c) {
        customerRepository.save(c);
    }
}
