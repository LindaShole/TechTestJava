package za.co.anycompany.service.Impl;

import org.springframework.stereotype.Service;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Order;
import za.co.anycompany.service.OrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  public OrderServiceImpl(OrderRepository orderRepository){
    this.orderRepository = orderRepository;
  }

  @Override
  public void createOrder(Order order){
    orderRepository.save(order);
  }

  @Override
  public void updateOrder(Order order){
    orderRepository.save(order);
  }

  @Override
  public void deleteOrder(Order order){
    orderRepository.delete(order);
  }

  @Override
  public void deleteOrder(int orderId){
    orderRepository.findById(orderId);
  }

  @Override
  public Optional<Order> getOrderById(int orderId){
    return orderRepository.findById(orderId);
  }

  @Override
  public List<Order> getAllOrders(){
    return (List<Order>) orderRepository.findAll();
  }
}
