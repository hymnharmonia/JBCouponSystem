package com.galfuchs.coupons.web.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galfuchs.coupons.core.entities.CompanyEntity;
import com.galfuchs.coupons.core.exceptions.ApplicationException;
import com.galfuchs.coupons.core.logic.CompanyController;

@RestController
@RequestMapping(value="/companies")
public class CompanyApi {
	
	@Autowired
	private CompanyController companyController;
	
	@PostMapping
	public long createCompany(@RequestBody CompanyEntity company) throws ApplicationException {
		return companyController.createCompany(company);
	}

	@GetMapping("/{companyId}")
	public CompanyEntity getCompany(@PathVariable("companyId") long companyId) throws ApplicationException {
		return companyController.getCompany(companyId);
	}
	
	@GetMapping
	public Collection<CompanyEntity> getAllCompanies() throws ApplicationException {
		return companyController.getAllCompanies();
	}
	
	@PutMapping
	public void updateCompany(@RequestBody CompanyEntity company) throws ApplicationException {
		System.out.println(company);
		companyController.updateCompany(company);
	}
	
	@DeleteMapping("/{companyId}")
	public void removeCompany(@PathVariable long companyId) throws ApplicationException {
		companyController.removeCompany(companyId);
	}
	
}
