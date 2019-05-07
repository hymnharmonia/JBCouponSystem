import { Subscription } from 'rxjs';
import { CompanyDataService } from 'src/app/services/dataServices/company-data.service';
import { Coupon } from './../../../../models/coupon.model';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { CouponDataService } from 'src/app/services/dataServices/coupon-data.service';

@Component({
  selector: 'app-create-coupon',
  templateUrl: './create-coupon.component.html',
  styleUrls: ['./create-coupon.component.css']
})
export class CreateCouponComponent implements OnInit, OnDestroy {
  coupon = new Coupon();
  message: string;
  createCouponSubscription: Subscription;

  constructor(private couponDataService: CouponDataService, private companyDataService: CompanyDataService) { }

  ngOnInit() {
    this.coupon.companyId = this.companyDataService.activeCompany.id;
  }

  onSubmit() {
    this.createCouponSubscription = this.couponDataService.createCoupon(this.coupon).subscribe(
      couponId => this.message = 'Coupon created, ID is: ' + couponId,
      (error) => this.message = error.error.internalMessage
    );
  }

  ngOnDestroy() {
    if (this.createCouponSubscription !== undefined) {
    this.createCouponSubscription.unsubscribe();
    }
  }
}
