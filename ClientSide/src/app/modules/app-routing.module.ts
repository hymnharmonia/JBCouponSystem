import { CustomerEditComponent } from './../components/body/customer/customer-edit/customer-edit.component';
import { AllCouponsResolver } from './../services/resolvers/all-coupons-resolver.service';
import { CustomerCouponsResolver } from './../services/resolvers/customer-coupons-resolver.service';
import { CustomerResolver } from './../services/resolvers/customer-resolver.service';
import { CustomerComponent } from './../components/body/customer/customer/customer.component';
import { CouponResolver } from './../services/resolvers/coupon-resolver.service';
import { CompanyCouponsResolver } from './../services/resolvers/company-coupons-resolver.service';
import { CompanyResolver } from '../services/resolvers/company-resolver.service';
import { AuthGuard } from './../services/auth-guard.service';
import { CouponDetailComponent } from './../components/body/coupons/coupon-detail/coupon-detail.component';
import { CompanyEditComponent } from './../components/body/company/company-edit/company-edit.component';
import { CompanyComponent } from './../components/body/company/company/company.component';
import { LoginComponent } from './../components/body/login/login.component';
import { DefaultComponent } from './../components/body/default/default.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { CreateCouponComponent } from '../components/body/coupons/create-coupon/create-coupon.component';
import { CouponListComponent } from '../components/body/coupons/coupon-list/coupon-list.component';

const appRoutes: Routes = [
  { path: '', component: DefaultComponent, pathMatch: 'full'},
  { path: 'login', component: LoginComponent },
  { path: 'company/:id', canActivate: [AuthGuard], component: CompanyComponent, resolve: {company: CompanyResolver}, children: [
    { path: '', redirectTo: 'coupons', pathMatch: 'full' },
    { path: 'createCoupon', component: CreateCouponComponent },
    { path: 'edit', component: CompanyEditComponent },
    { path: 'coupons', component: CouponListComponent, resolve: {coupons: CompanyCouponsResolver}},
    { path: 'coupons/:id', component: CouponDetailComponent, resolve: {coupon: CouponResolver} }
  ] },
  { path: 'customer/:id', canActivate: [AuthGuard], component: CustomerComponent, resolve: {customer: CustomerResolver}, children: [
    { path: '', redirectTo: 'allCoupons', pathMatch: 'full'},
    { path: 'edit', component: CustomerEditComponent},
    { path: 'myCoupons', component: CouponListComponent, resolve: {coupons: CustomerCouponsResolver} },
    { path: 'myCoupons/:id', component: CouponDetailComponent, resolve: {coupon: CouponResolver} },
    { path: 'allCoupons', component: CouponListComponent, resolve: {coupons: AllCouponsResolver} },
    { path: 'allCoupons/:id', component: CouponDetailComponent, resolve: {coupon: CouponResolver}}
  ]}
];

@NgModule({
imports: [
  RouterModule.forRoot(appRoutes)
],
exports: [RouterModule]
})

export class AppRoutingModule { }
