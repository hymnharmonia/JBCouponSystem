import { Subscription } from 'rxjs';
import { CompanyDataService } from 'src/app/services/dataServices/company-data.service';
import { Company } from './../../../../models/company.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-company-edit',
  templateUrl: './company-edit.component.html',
  styleUrls: ['./company-edit.component.css']
})
export class CompanyEditComponent implements OnInit {

  updateCompany = new Company();
  updateCompanySubscription: Subscription;

  message: string;

  constructor(private companyDataService: CompanyDataService) { }

  ngOnInit() {
    this.updateCompany = this.companyDataService.activeCompany;
  }

  onSubmit() {
    this.updateCompanySubscription = this.companyDataService.updateCompany(this.updateCompany).subscribe(
      () => this.message = 'Company updated successfuly',
      error => this.message = error.error.internalMessage
    );
  }

}
