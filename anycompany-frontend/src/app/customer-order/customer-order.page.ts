import { OrderService } from './../services/order-service';
import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../services/customer-service';
import { Order } from '../model/order.model';
import { Customer } from '../model/customer.model';
import { CustomerOrderDetails } from '../model/customer-order.model';

@Component({
  selector: 'app-customer-order',
  templateUrl: './customer-order.page.html',
  styleUrls: ['./customer-order.page.scss'],
})
export class CustomerOrderPage implements OnInit {
  customerOrderDetails: CustomerOrderDetails[] = [];
  constructor(private customerService: CustomerService,
              private orderService: OrderService)
              { }

  ngOnInit() {
    this.customerService.fetchAllCustomers().subscribe(async (customers) => {
      customers.forEach(element => {
        this.orderService.fetchAllCustomerOrdersById(element.customerId).subscribe(async(result) => {
          const customerOrderDetails: CustomerOrderDetails  = new CustomerOrderDetails(
            result[0].orderId,
            element.customerId,
            element.name,
            element.country,
            element.dateOfBirth,
            result[0].amount,
            result[0].vat,
          );
          this.customerOrderDetails.push(customerOrderDetails);
        });
      });
    });
  }
}
