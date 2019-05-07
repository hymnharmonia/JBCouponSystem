import { HttpErrorResponse } from '@angular/common/http';
import { Coupon } from './../../models/coupon.model';
import { Injectable } from '@angular/core';
import { CouponApiService } from '../apiServices/coupon-api.service';
import { Subject } from 'rxjs/Subject';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class CouponDataService {

  constructor(private couponApiService: CouponApiService, private router: Router) { }

  getAllCoupons() {
    return this.couponApiService.getAllCoupons();
  }

  getCompanyCoupons(companyId: number) {
    return this.couponApiService.getCompanyCoupons(companyId);
  }

  getCustomerCoupons(customerId: number) {
    return this.couponApiService.getCustomerCoupons(customerId);
  }

  getCoupon(couponId: number) {
    return this.couponApiService.getCoupon(couponId);
  }

  createCoupon(coupon: Coupon) {
    return this.couponApiService.createCoupon(coupon);
  }

  deleteCoupon(couponId: number) {
    return this.couponApiService.deleteCoupon(couponId);
  }

  updateCoupon(coupon: Coupon) {
    return this.couponApiService.updateCoupon(coupon);
  }
}
