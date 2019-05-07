import { CouponDataService } from 'src/app/services/dataServices/coupon-data.service';
import { Coupon } from '../../models/coupon.model';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class CustomerCouponsResolver implements Resolve<Coupon[]> {

  constructor(private couponDataService: CouponDataService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.couponDataService.getCustomerCoupons(+route.parent.params['id']);
  }

}
