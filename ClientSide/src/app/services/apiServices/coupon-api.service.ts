import { Coupon } from './../../models/coupon.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CouponApiService {

  constructor(private http: HttpClient) { }

  getAllCoupons(): Observable<Coupon[]> {
    return this.http.get<Coupon[]>('http://localhost:8080/CouponSystemWeb/rest/coupons', {withCredentials: true});
  }

  createCoupon(coupon: Coupon): Observable<number> {
    return this.http.post<number>('http://localhost:8080/CouponSystemWeb/rest/coupons/', coupon, {withCredentials: true });
  }

  getCompanyCoupons(companyId: number): Observable<Coupon[]> {
    return this.http.get<Coupon[]>('http://localhost:8080/CouponSystemWeb/rest/coupons/company/' + companyId, {withCredentials: true});
  }

  getCustomerCoupons(customerId: number): Observable<Coupon[]> {
    return this.http.get<Coupon[]>('http://localhost:8080/CouponSystemWeb/rest/coupons/customer/' + customerId, {withCredentials: true});
  }

  getCoupon(couponId: number): Observable<Coupon> {
    return this.http.get<Coupon>('http://localhost:8080/CouponSystemWeb/rest/coupons/' + couponId, { withCredentials: true});
  }

  deleteCoupon(couponId: number): Observable<boolean> {
    return this.http.delete<boolean>('http://localhost:8080/CouponSystemWeb/rest/coupons/' + couponId, { withCredentials: true});
  }

  updateCoupon(coupon: Coupon): Observable<boolean> {
    return this.http.put<boolean>('http://localhost:8080/CouponSystemWeb/rest/coupons/', coupon, { withCredentials: true});
  }
}
