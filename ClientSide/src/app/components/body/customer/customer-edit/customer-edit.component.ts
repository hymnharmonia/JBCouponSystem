import { CustomerDataService } from './../../../../services/dataServices/customer-data.service';
import { Subscription } from 'rxjs';
import { Customer } from './../../../../models/customer.model';
import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-customer-edit',
  templateUrl: './customer-edit.component.html',
  styleUrls: ['./customer-edit.component.css']
})
export class CustomerEditComponent implements OnInit, OnDestroy {

  updateCustomer = new Customer();
  updateCustomerSubscription: Subscription;

  message: string;

  constructor(private customerDataService: CustomerDataService) { }

  ngOnInit() {
    this.updateCustomer = this.customerDataService.activeCustomer;
  }

  onSubmit() {
    this.updateCustomerSubscription = this.customerDataService.updateCustomer(this.updateCustomer).subscribe(
      () => this.message = 'Customer updated successfuly',
      error => this.message = error.error.internalMessage
    );
  }

  ngOnDestroy() {
    if (this.updateCustomerSubscription !== undefined) {
      this.updateCustomerSubscription.unsubscribe();
    }
  }

}
