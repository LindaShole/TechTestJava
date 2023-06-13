using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AnyCompanyEntertainmentDotNet.Models
{
    public class Customer
    {
        public Customer()
        {
            this.Orders = new List<Order>();
        }
        public int Id { get; set; }
        public string Name { get; set; }
        public string? country { get; set; }
        public DateTime dateOfBirth { get; set; }
        public List<Order> Orders { get; set; }
    }

}
