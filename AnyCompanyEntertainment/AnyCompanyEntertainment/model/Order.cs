using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AnyCompanyEntertainment_.model
{
    public class Order
    {

        private int orderId;
        private double amount;
        private double VAT;

        public int getOrderId()
        {
            return orderId;
        }

        public void setOrderId(int orderId)
        {
            this.orderId = orderId;
        }

        public double getAmount()
        {
            return amount;
        }

        public void setAmount(double amount)
        {
            this.amount = amount;
        }

        public double getVAT()
        {
            return VAT;
        }

        public void setVAT(double VAT)
        {
            this.VAT = VAT;
        }
    }

}
