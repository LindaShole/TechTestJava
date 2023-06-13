using AnyCompanyEntertainmentDotNet.Models;
using AnyCompanyEntertainmentDotNet.Services.Repositories.Interfaces;

namespace AnyCompanyEntertainmentDotNet.Services.Repositories
{
    public class OrderRepository : IOrderRepository
    {
        private readonly AppDbContext dbContext;

        public OrderRepository(AppDbContext _dbContext)
        {
            dbContext = _dbContext;
        }

        public Order? GetById(int id)
        {
            return dbContext.Orders.FirstOrDefault(o => o.Id == id);
        }

        public List<Order> GetAllByCustomerId(int customerId)
        {
            return dbContext.Orders.Where(o => o.CustomerId == customerId).ToList();
        }

        public void Add(Order order)
        {
            dbContext.Orders.Add(order);
            dbContext.SaveChanges();
        }
    }

}
