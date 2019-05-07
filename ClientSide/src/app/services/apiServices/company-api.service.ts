import { Company } from './../../models/company.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CompanyApiService {

  constructor(private http: HttpClient) { }

  getCompany(companyId: number): Observable<Company> {
    return this.http.get<Company>('http://localhost:8080/CouponSystemWeb/rest/companies/' + companyId, {withCredentials: true});
  }

  updateCompany(company: Company): Observable<void> {
    return this.http.put<void>('http://localhost:8080/CouponSystemWeb/rest/companies/', company, {withCredentials: true});
  }
}
