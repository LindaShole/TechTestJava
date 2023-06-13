using AnyCompanyEntertainment_.datalayer;
using AnyCompanyEntertainment_.model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AnyCompanyEntertainment_.service
{
    public class OrderService
    {

        private OrderRepository orderRepository = new OrderRepository();

        public Boolean placeOrder(Order order, int customerId)
        {
            Customer customer = CustomerRepository.load(customerId);

            if (order.getAmount() == 0)
                return false;

            if (customer.getCountry() == "UK")
                order.setVAT(0.2d);
            else
                order.setVAT(0);

            orderRepository.save(order);

            return true;
        }
    }

}
