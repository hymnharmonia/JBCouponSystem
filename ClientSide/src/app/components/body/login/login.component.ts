import { CouponDataService } from 'src/app/services/dataServices/coupon-data.service';
import { CompanyDataService } from 'src/app/services/dataServices/company-data.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { Credentials } from 'src/app/models/credentials.model';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private loginService: LoginService, private router: Router,
     private couponDataService: CouponDataService, private companyDataService: CompanyDataService) {}

  credentials = new Credentials();

  loginSubscription: Subscription;

  message: string;

  ngOnInit() {
    this.loginService.checkLogin();
  }

  login() {
    this.loginSubscription = this.loginService
      .doLogin(this.credentials)
      .subscribe(
        userFromServer => {
          this.loginService.isLoggedIn = true;
          this.loginService.activeUser = userFromServer;
          this.loginService.loginSubmitted = true;
          this.processLogin(userFromServer);
        },
        (error: HttpErrorResponse) => {
          this.message = error.error.internalMessage;
        }
      );
  }

  processLogin(userFromServer: User) {
    if (userFromServer.clientType === 'Company') {
      this.router.navigate(['company', userFromServer.id]);
    } else if (userFromServer.clientType === 'Customer') {
      this.router.navigate(['customer', userFromServer.id]);
    } else if (userFromServer.clientType === 'Admin') {
      // TO BE CONTINUED
      this.router.navigate(['admin']);
    }
  }

}
