import { CustomerDataService } from './../../../../services/dataServices/customer-data.service';
import { CouponDataService } from './../../../../services/dataServices/coupon-data.service';
import { ActivatedRoute, Data } from '@angular/router';
import { Customer } from './../../../../models/customer.model';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit, OnDestroy {

  activeCustomer: Customer;

  customerSubscription: Subscription;

  constructor(private route: ActivatedRoute, private customerDataService: CustomerDataService) {}

  ngOnInit() {
    this.customerSubscription = this.route.data.subscribe(
    (data: Data) => this.activeCustomer = this.customerDataService.activeCustomer = data['customer']
    );
  }

  ngOnDestroy() {
    this.customerSubscription.unsubscribe();
  }

}
