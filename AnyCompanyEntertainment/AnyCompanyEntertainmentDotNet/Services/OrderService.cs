using AnyCompanyEntertainmentDotNet.Models;
using AnyCompanyEntertainmentDotNet.Services.Interfaces;
using AnyCompanyEntertainmentDotNet.Services.Repositories.Interfaces;

namespace AnyCompanyEntertainmentDotNet.Services
{
    public class OrderService : IOrderService
    {
        private readonly ICustomerRepository customerRepository;
        private readonly IOrderRepository orderRepository;
        private readonly AppDbContext dbContext;

        public OrderService(ICustomerRepository customerRepository, IOrderRepository orderRepository, AppDbContext dbContext)
        {
            this.customerRepository = customerRepository;
            this.orderRepository = orderRepository;
            this.dbContext = dbContext;
        }

        public void PlaceOrder(int customerId, Order order)
        {
            Customer? customer = customerRepository.GetById(customerId);
            if (customer != null)
            {
                order.CustomerId = customerId;
                orderRepository.Add(order);
                Console.WriteLine("Order placed successfully.");
            }
            else
            {
                Console.WriteLine("Customer not found.");
            }
        }

        public Customer? GetCustomerWithOrders(int customerId)
        {
            return customerRepository.GetById(customerId);
        }

        public List<Customer> GetAllCustomersWithOrders()
        {
            return customerRepository.GetAll();
        }
    }
}
