using AnyCompanyEntertainmentDotNet.Models;
using AnyCompanyEntertainmentDotNet.Services.Repositories.Interfaces;
using Microsoft.EntityFrameworkCore;

namespace AnyCompanyEntertainmentDotNet.Services.Repositories
{
    public class CustomerRepository : ICustomerRepository
    {
        private readonly AppDbContext dbContext;

        public CustomerRepository(AppDbContext _dbContext)
        {
            dbContext = _dbContext;
        }

        public Customer? GetById(int id)
        {
            return dbContext.Customers.Include(c => c.Orders).FirstOrDefault(c => c.Id == id);
        }
        public Customer? GetCustomerByName(string customerName)
        {
            return dbContext.Customers.Include(c => c.Orders).FirstOrDefault(c => c.Name == customerName);
        }
        public List<Customer> GetAll()
        {
            return dbContext.Customers.Include(c => c.Orders).ToList();
        }

        public void Add(Customer customer)
        {
            dbContext.Customers.Add(customer);
            dbContext.SaveChanges();
        }
    }
}
