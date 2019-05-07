import { CustomerDataService } from './../../../../services/dataServices/customer-data.service';
import { LoginService } from './../../../../services/login.service';
import { CouponDataService } from 'src/app/services/dataServices/coupon-data.service';
import { Coupon } from './../../../../models/coupon.model';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Data } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-coupon-detail',
  templateUrl: './coupon-detail.component.html',
  styleUrls: ['./coupon-detail.component.css']
})
export class CouponDetailComponent implements OnInit, OnDestroy {

  edit = false;

  purchased: boolean;

  deleted: boolean;

  couponSubscription: Subscription;

  updateSubscription: Subscription;

  deleteSubscription: Subscription;

  purchaseSubscription: Subscription;

  coupon: Coupon;

  message: string;

  constructor(private route: ActivatedRoute, private couponDataService: CouponDataService, private loginService: LoginService,
     private customerDataService: CustomerDataService) { }

  clientType: string;

  ngOnInit() {
    this.couponSubscription = this.route.data.subscribe(
      (data: Data) => this.coupon = data['coupon']);
    this.clientType = this.loginService.activeUser.clientType;
    if (this.route.snapshot.url[0].path === 'myCoupons') {
      this.purchased = true;
    }
  }

  ngOnDestroy() {
    this.couponSubscription.unsubscribe();
    if (this.updateSubscription !== undefined) {
    this.updateSubscription.unsubscribe();
    }
    if (this.deleteSubscription !== undefined) {
      this.deleteSubscription.unsubscribe();
    }
    if (this.purchaseSubscription !== undefined) {
      this.purchaseSubscription.unsubscribe();
    }
  }

  onEdit() {
    this.edit = true;
  }

  onDelete(couponId: number) {
    this.deleteSubscription = this.couponDataService.deleteCoupon(couponId).subscribe(
      result => { this.deleted = result;
      this.message = 'Coupon deleted successfuly'; },
      error => this.message = error.error.internalMessage);
  }

  onPurchase(couponId: number) {
    const customerId = +this.route.snapshot.parent.params['id'];
    this.purchaseSubscription = this.customerDataService.purchaseCoupon(couponId, customerId).subscribe(
      () => { this.purchased = true;
       this.message = 'Coupon purchased successfuly'; },
      error => this.message = error.error.internalMessage);
  }

  onSubmit() {
    this.updateSubscription = this.couponDataService.updateCoupon(this.coupon).subscribe(
      () => this.message = 'coupon updated',
      (error) => this.message = error.error.internalMessage
    );
  }

}
