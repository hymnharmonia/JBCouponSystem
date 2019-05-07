import { ActivatedRoute, Data } from '@angular/router';
import { Subscription } from 'rxjs';
import { Coupon } from './../../../../models/coupon.model';
import { Component, OnInit, OnChanges, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-coupon-list',
  templateUrl: './coupon-list.component.html',
  styleUrls: ['./coupon-list.component.css']
})
export class CouponListComponent implements OnInit, OnDestroy {
  coupons: Coupon[];

  couponsSubscription: Subscription;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.couponsSubscription = this.route.data.subscribe(
      (data: Data) => this.coupons = data['coupons']
    );
  }

  ngOnDestroy() {
    this.couponsSubscription.unsubscribe();
  }

}
