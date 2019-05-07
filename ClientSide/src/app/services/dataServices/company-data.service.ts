import { Company } from './../../models/company.model';
import { CompanyApiService } from './../apiServices/company-api.service';
import { Injectable, OnDestroy } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CompanyDataService {
  activeCompany = new Company();

  constructor(private companyApiService: CompanyApiService) {}

  getCompany(companyId: number) {
    return this.companyApiService.getCompany(companyId);
  }

  updateCompany(company: Company) {
    return this.companyApiService.updateCompany(company);
  }
}
