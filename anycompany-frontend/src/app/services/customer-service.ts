import { Injectable } from '@angular/core';
import { Customer } from '../model/customer.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private baseUrl = 'http://localhost:8080/api/customers';
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  constructor(private http: HttpClient) {}

  addCustomer(customer: Customer): Observable<object> {
    return this.http.post(`${this.baseUrl}`, customer);
  }

  fetchAllCustomers(): Observable<any> {
    return this.http.get(`${this.baseUrl}`, this.httpOptions);
  }
}
