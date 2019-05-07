import { Customer } from './../../models/customer.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CustomerApiService {

  customerUrl = 'http://localhost:8080/CouponSystemWeb/rest/customers/';

  constructor(private http: HttpClient) { }

  getCustomer(customerId: number): Observable<Customer> {
    return this.http.get<Customer>(this.customerUrl + customerId, {withCredentials: true});
  }

  updateCustomer(customer: Customer): Observable<void> {
    return this.http.put<void>(this.customerUrl, customer, {withCredentials: true});
  }

  purchaseCoupon(couponId: number, customerId: number): Observable<void> {
    return this.http.get<void>(this.customerUrl + 'purchase/' + customerId + '/' + couponId, { withCredentials: true});
  }
}
