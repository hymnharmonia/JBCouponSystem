import { Coupon } from './coupon.model';

export class Customer {

  public constructor(public id?: number, public custName?: string,
     public password?: string, public email?: string, public coupons?: Coupon[]) {}

}
