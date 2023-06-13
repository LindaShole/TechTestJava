using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AnyCompanyEntertainmentDotNet.Models
{
    public class Order
    {
        public int Id { get; set; }
        public int CustomerId { get; set; }
        public string? ProductName { get; set; }
        public decimal Price { get; set; }
        public Customer? Customer { get; set; }
    }
}
