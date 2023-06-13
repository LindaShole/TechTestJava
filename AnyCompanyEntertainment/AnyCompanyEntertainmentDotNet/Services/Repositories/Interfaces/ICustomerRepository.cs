using AnyCompanyEntertainmentDotNet.Models;

namespace AnyCompanyEntertainmentDotNet.Services.Repositories.Interfaces
{

    public interface ICustomerRepository
    {
        Customer? GetById(int id);
        List<Customer> GetAll();
        void Add(Customer customer);
        Customer? GetCustomerByName(string customerName);

    }
}
