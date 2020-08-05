import { Order } from './../model/order.model';
import { Customer } from './../model/customer.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlertController } from '@ionic/angular';
import { CustomerService } from '../services/customer-service';
import { OrderService } from '../services/order-service';

@Component({
  selector: 'app-folder',
  templateUrl: './folder.page.html',
  styleUrls: ['./folder.page.scss'],
})
export class FolderPage implements OnInit {
  public folder: string;
  public amount = 150;
  public mealName = 'Combo 5';
  private customerId: number;
  private orderId: number;
  public customerName: string;
  public country: string;
  public dateOfBirth: Date;
  constructor(private activatedRoute: ActivatedRoute,
              private customerService: CustomerService,
              private orderService: OrderService,
              private alertCtrl: AlertController)
              { }

  ngOnInit() {
    this.folder = this.activatedRoute.snapshot.paramMap.get('id');
  }

  placeCustomerOrder() {
    const randomId = Math.floor((Math.random() * 100) + 1);
    this.orderId = randomId;
    this.customerId = randomId;
    let customer: Customer;
    let order: Order;
    customer = new Customer (
      this.customerId,
      this.customerName,
      this.country,
      this.dateOfBirth
    );
    order = new Order(
      this.orderId,
      this.customerId,
      this.amount,
      0,
    );
    this.customerService.addCustomer(customer).subscribe(async (result) => {
      this.orderService.placeOrder(order).subscribe(async () => {
        const alert = await this.alertCtrl.create({
          cssClass: 'my-custom-class',
          subHeader: 'Order',
          message:
            'Thanks for placing an order with us, your food will arrive shortly',
          buttons: ['Ok'],
        });
        await alert.present();
      });
    });
  }
}
