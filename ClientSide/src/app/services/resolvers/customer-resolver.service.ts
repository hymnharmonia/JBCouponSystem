import { CustomerDataService } from './../dataServices/customer-data.service';
import { Customer } from './../../models/customer.model';
import { CompanyDataService } from 'src/app/services/dataServices/company-data.service';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class CustomerResolver implements Resolve<Customer> {

  constructor(private customerDataService: CustomerDataService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.customerDataService.getCustomer(+route.params['id']);
  }

}
