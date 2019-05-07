import { Company } from './../../../../models/company.model';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-company-info',
  templateUrl: './company-info.component.html',
  styleUrls: ['./company-info.component.css']
})
export class CompanyInfoComponent implements OnInit {
  @Input() activeCompany: Company;

  constructor() { }

  ngOnInit() {
  }

}
