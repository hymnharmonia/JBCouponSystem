import { Company } from './../../models/company.model';
import { CompanyDataService } from 'src/app/services/dataServices/company-data.service';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class CompanyResolver implements Resolve<Company> {

  constructor(private companyDataService: CompanyDataService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.companyDataService.getCompany(+route.params['id']);
  }

}
