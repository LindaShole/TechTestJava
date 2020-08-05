import { Injectable } from '@angular/core';
import { Order } from '../model/order.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private baseUrl = 'http://localhost:8080/api/orders';
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  constructor(private http: HttpClient) {}

  placeOrder(order: Order): Observable<object> {
    return this.http.post(`${this.baseUrl}`, order);
  }

  fetchAllCustomerOrdersById(orderId: number): Observable<any> {
      return this.http.get(`${this.baseUrl}/${orderId}`, this.httpOptions);
  }

  fetchAllOrders(): Observable<any> {
    return this.http.get(`${this.baseUrl}`, this.httpOptions);
  }
}
