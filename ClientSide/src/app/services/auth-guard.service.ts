import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlSegment } from '@angular/router';
import { LoginService } from './login.service';

@Injectable()
export class AuthGuard  implements CanActivate {

  constructor(private loginService: LoginService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    return this.hasRequiredPermission(route.url[0].path);
  }

  hasRequiredPermission(url: string) {
    const clientType = this.loginService.activeUser.clientType;
    if (url === 'company' && clientType === 'Company') {
      return true;
    }
    if (url === 'customer' && clientType === 'Customer') {
      return true;
    }
    return false;
  }

}
