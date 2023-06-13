using AnyCompanyEntertainmentDotNet.Models;

namespace AnyCompanyEntertainmentDotNet.Services.Interfaces
{
    public interface IOrderService
    {
        void PlaceOrder(int customerId, Order order);
        Customer? GetCustomerWithOrders(int customerId);
        List<Customer> GetAllCustomersWithOrders();
    }
}
