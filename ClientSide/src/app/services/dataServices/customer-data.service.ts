import { CustomerApiService } from './../apiServices/customer-api.service';
import { Customer } from './../../models/customer.model';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CustomerDataService {
  activeCustomer = new Customer();

  constructor(private customerApiService: CustomerApiService) {}

  getCustomer(customerId: number) {
    return this.customerApiService.getCustomer(customerId);
  }

  updateCustomer(customer: Customer) {
    return this.customerApiService.updateCustomer(customer);
  }

  purchaseCoupon(couponId: number, customerId: number) {
    return this.customerApiService.purchaseCoupon(couponId, customerId);
  }
}
