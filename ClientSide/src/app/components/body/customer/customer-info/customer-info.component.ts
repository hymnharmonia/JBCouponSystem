import { Customer } from './../../../../models/customer.model';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-customer-info',
  templateUrl: './customer-info.component.html',
  styleUrls: ['./customer-info.component.css']
})
export class CustomerInfoComponent implements OnInit {
  @Input() activeCustomer: Customer;

  constructor() { }

  ngOnInit() {
  }

}
