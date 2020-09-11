package za.co.anycompany.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import za.co.anycompany.datalayer.CustomerOrderRepository;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.dto.CustomerDTO;
import za.co.anycompany.dto.OrderDTO;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.CustomerOrder;
import za.co.anycompany.model.Order;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    public boolean placeOrder(Order order, int customerId) {

        if (order.getAmount() == 0)
            return false;

        OrderRepository orderRepository = new OrderRepository();
        CustomerOrderRepository customerOrderRepository = new CustomerOrderRepository();
        CustomerOrder customerOrder = new CustomerOrder();

        Customer customer = CustomerRepository.load(customerId);

        order.setVAT(setVat(customer.getCountry()));

        customerOrder.setOrderId(orderRepository.save(order));
        customerOrder.setCustomerId(customerId);

        return customerOrderRepository.save(customerOrder);
    }

    public List<CustomerDTO> loadCustomers(){

        List<CustomerDTO> customerDTOS = new ArrayList<>() ;

        List<Customer> customerList = getCustomers();
        customerDTOS = getCustomerDTOS(customerList);
        return customerDTOS;
    }

    public CustomerDTO getCustomer(int customerId){
        List<Customer> customerListTemp = new ArrayList<>();

        Customer customer = CustomerRepository.load(customerId);
        customerListTemp.add(customer);
        CustomerDTO customerDTO  = getCustomerDTOS(customerListTemp).get(0);
        return customerDTO;
    }

    private List<Customer> getCustomers(){
        List<Customer> customers = CustomerRepository.load();
        return customers;
    }

    private List<CustomerDTO> getCustomerDTOS(List<Customer> customerList){
        List<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();
        List<CustomerOrder> customerOrderList = new ArrayList<CustomerOrder>();

        for (Customer customer: customerList){
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCountry(customer.getCountry());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setName(customer.getName());

            customerOrderList.addAll(CustomerOrderRepository.load(customer.getCustomerId()));
            List<OrderDTO> orderDTOS = new ArrayList<OrderDTO>();
            for (CustomerOrder customerOrder: customerOrderList){
                Order order =  OrderRepository.load(customerOrder.getOrderId());
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setAmount(order.getAmount());
                orderDTO.setOrderId(order.getOrderId());
                orderDTO.setVAT(order.getVAT());
                orderDTOS.add(orderDTO);
            }
            customerDTO.setOrderDTOList(orderDTOS);
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }

    private double setVat(String country){
        if (country == "UK")
            return 0.2d;
        else
            return 0;
    }
}
