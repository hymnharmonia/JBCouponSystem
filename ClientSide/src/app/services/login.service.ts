import { CouponDataService } from 'src/app/services/dataServices/coupon-data.service';
import { CompanyDataService } from 'src/app/services/dataServices/company-data.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from './../models/user.model';
import { Injectable } from '@angular/core';
import { Credentials } from '../models/credentials.model';
import { LoginApiService } from './apiServices/login-api.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(
    private loginApiService: LoginApiService,
    private router: Router,
    private companyDataService: CompanyDataService,
    private couponDataService: CouponDataService
  ) {}

  loginSubmitted = false;
  isLoggedIn: boolean;
  activeUser = new User();

  public doLogin(credentials: Credentials) {
    return this.loginApiService.login(credentials);
  }

  public doLogout() {
    if (this.isLoggedIn) {
      const observable = this.loginApiService.logout();
      observable.subscribe(
        () => {
          sessionStorage.clear();
          this.isLoggedIn = false;
          this.loginSubmitted = false;
          this.activeUser = new User();
        }
      );
    }
  }

  checkLogin() {
    if (this.isLoggedIn) {
      return this.activeUser.clientType;
    }
    return false;
  }

}
