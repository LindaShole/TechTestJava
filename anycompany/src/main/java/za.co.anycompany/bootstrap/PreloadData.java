package za.co.anycompany.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.datalayer.ProductRepository;
import za.co.anycompany.model.Address;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.model.Product;

import java.util.Arrays;
import java.util.Date;

// Used for testing
@Component
public class PreloadData implements CommandLineRunner {

  private static final Logger LOG = LoggerFactory.getLogger(PreloadData.class);
  private final CustomerRepository customerRepository;
  private final OrderRepository orderRepository;


  private final ProductRepository productRepository;

  public PreloadData(CustomerRepository customerRepository, OrderRepository orderRepository,
                     ProductRepository productRepository){
    this.customerRepository = customerRepository;
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
  }

  @Override
  public void run(String... args) throws Exception{

    Address   address = new Address("66 Kingsway ave","Durban","","");

    Customer customer = new Customer("Vusi","RSA", new Date(),"79451111",address);

    Product product = new Product("Nike air ",2,400);
    Product productx = new Product("Nike Jordan ",3,7000);
    Product playStation = new Product("Play Station 5",5,200);

    Order order = new Order();
    Order order2 = new Order();

    productx.setOrder(order2);
    playStation.setOrder(order2);
    product.setOrder(order);

    order2.setCustomer(customer);
    order2.setProducts(Arrays.asList(playStation,product));

    order.setCustomer(customer);
    order.setProducts(Arrays.asList(productx));



    customer.setOrders(Arrays.asList(order, order2));
    customerRepository.save(customer);

    orderRepository.save(order);
    orderRepository.save(order2);

    productRepository.save(product);
    productRepository.save(playStation);
    productRepository.save(productx);





  }
}
