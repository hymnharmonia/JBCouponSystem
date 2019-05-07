import { CompanyDataService } from './../../../../services/dataServices/company-data.service';
import { ActivatedRoute, Data } from '@angular/router';
import { Subscription } from 'rxjs';
import { CouponDataService } from './../../../../services/dataServices/coupon-data.service';
import { Company } from './../../../../models/company.model';
import { Component, OnInit, OnDestroy, Output } from '@angular/core';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit, OnDestroy {

  @Output() activeCompany: Company;

  companySubscription: Subscription;

  constructor(private route: ActivatedRoute, private companyDataService: CompanyDataService) {
  }

  ngOnInit() {
    this.companySubscription = this.route.data.subscribe(
      (data: Data) => this.activeCompany = this.companyDataService.activeCompany = data['company']
    );
  }

  ngOnDestroy() {
    this.companySubscription.unsubscribe();
  }

}
