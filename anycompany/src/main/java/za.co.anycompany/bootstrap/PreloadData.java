package za.co.anycompany.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.util.Arrays;
import java.util.Date;

@Component
public class PreloadData implements CommandLineRunner {

  private static final Logger LOG = LoggerFactory.getLogger(PreloadData.class);
  private final CustomerRepository customerRepository;

  private final OrderRepository orderRepository;

  public PreloadData(CustomerRepository customerRepository, OrderRepository orderRepository){
    this.customerRepository = customerRepository;
    this.orderRepository = orderRepository;
  }

  @Override
  public void run(String... args) throws Exception{

    Customer customer = new Customer("Vusi","RSA", new Date());

    Order order = new Order(50,20,"iPhone", customer);
    Order order2 = new Order(500,2,"Play Station 5", customer);
    Order order3 = new Order(50,0,"Other", customer);



    customer.setOrders(Arrays.asList(order, order2, order3));
    customerRepository.save(customer);

    orderRepository.save(order);
    orderRepository.save(order2);
    orderRepository.save(order3);

  }
}
