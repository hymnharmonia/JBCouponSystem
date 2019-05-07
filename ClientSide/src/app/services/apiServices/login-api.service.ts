import { Observable } from 'rxjs';
import { Credentials } from './../../models/credentials.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/models/user.model';

@Injectable({
  providedIn: 'root'
})
export class LoginApiService {

  constructor(private http: HttpClient) { }


  login(credentials: Credentials): Observable<User> {
    return this.http.post<User>('http://localhost:8080/CouponSystemWeb/rest/login', credentials, { responseType: 'json', withCredentials: true});
  }

  logout() {
    return this.http.delete<void>('http://localhost:8080/CouponSystemWeb/rest/login', { withCredentials: true });
  }
}
