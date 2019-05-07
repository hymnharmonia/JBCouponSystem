import { CouponDataService } from 'src/app/services/dataServices/coupon-data.service';
import { Coupon } from '../../models/coupon.model';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class CompanyCouponsResolver implements Resolve<Coupon[]> {

  constructor(private couponDataService: CouponDataService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.couponDataService.getCompanyCoupons(+route.parent.params['id']);
  }

}
