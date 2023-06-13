using AnyCompanyEntertainmentDotNet.Models;

namespace AnyCompanyEntertainmentDotNet.Services.Repositories.Interfaces
{
    public interface IOrderRepository
    {
        Order? GetById(int id);
        List<Order> GetAllByCustomerId(int customerId);
        void Add(Order order);
    }
}
