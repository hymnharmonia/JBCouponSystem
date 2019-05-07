import { CustomerEditComponent } from './../components/body/customer/customer-edit/customer-edit.component';
import { AllCouponsResolver } from './../services/resolvers/all-coupons-resolver.service';
import { CompanyResolver } from '../services/resolvers/company-resolver.service';
import { AuthGuard } from './../services/auth-guard.service';
import { CustomerDataService } from './../services/dataServices/customer-data.service';
import { CustomerApiService } from './../services/apiServices/customer-api.service';
import { CouponDataService } from './../services/dataServices/coupon-data.service';
import { CouponApiService } from './../services/apiServices/coupon-api.service';
import { CompanyDataService } from './../services/dataServices/company-data.service';
import { CompanyApiService } from './../services/apiServices/company-api.service';
import { LoginService } from './../services/login.service';
import { LoginApiService } from './../services/apiServices/login-api.service';
import { AppRoutingModule } from './app-routing.module';
import { DropdownDirective } from './../shared/dropdown.directive';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';

import { LayoutComponent } from './../components/layout/layout.component';
import { HeaderComponent } from '../components/header/header.component';
import { BodyComponent } from '../components/body/body.component';
import { FooterComponent } from '../components/footer/footer.component';
import { LoginComponent } from '../components/body/login/login.component';
import { CompanyComponent } from '../components/body/company/company/company.component';
import { DefaultComponent } from '../components/body/default/default.component';
import { CompanyInfoComponent } from '../components/body/company/company-info/company-info.component';
import { CouponListComponent } from '../components/body/coupons/coupon-list/coupon-list.component';
import { CouponItemComponent } from '../components/body/coupons/coupon-item/coupon-item.component';
import { CompanyEditComponent } from '../components/body/company/company-edit/company-edit.component';
import { CreateCouponComponent } from '../components/body/coupons/create-coupon/create-coupon.component';
import { CouponDetailComponent } from '../components/body/coupons/coupon-detail/coupon-detail.component';
import { CompanyCouponsResolver } from '../services/resolvers/company-coupons-resolver.service';
import { CouponResolver } from '../services/resolvers/coupon-resolver.service';
import { CustomerComponent } from '../components/body/customer/customer/customer.component';
import { CustomerInfoComponent } from '../components/body/customer/customer-info/customer-info.component';
import { CustomerResolver } from '../services/resolvers/customer-resolver.service';
import { CustomerCouponsResolver } from '../services/resolvers/customer-coupons-resolver.service';

@NgModule({
  declarations: [
    LayoutComponent,
    HeaderComponent,
    BodyComponent,
    FooterComponent,
    LoginComponent,
    CompanyComponent,
    DropdownDirective,
    DefaultComponent,
    CompanyInfoComponent,
    CouponListComponent,
    CouponItemComponent,
    CompanyEditComponent,
    CreateCouponComponent,
    CouponDetailComponent,
    CustomerComponent,
    CustomerInfoComponent,
    CustomerEditComponent
  ],
  imports: [BrowserModule, FormsModule, HttpClientModule, AppRoutingModule],
  providers: [
    LoginApiService,
    LoginService,
    CompanyApiService,
    CompanyDataService,
    CouponApiService,
    CouponDataService,
    CustomerApiService,
    CustomerDataService,
    HttpClient,
    AuthGuard,
    CompanyResolver,
    CompanyCouponsResolver,
    CouponResolver,
    CustomerResolver,
    CustomerCouponsResolver,
    AllCouponsResolver
  ],
  bootstrap: [LayoutComponent]
})
export class AppModule {}
