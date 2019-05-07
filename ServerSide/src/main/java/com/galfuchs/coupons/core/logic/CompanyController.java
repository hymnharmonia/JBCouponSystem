package com.galfuchs.coupons.core.logic;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.galfuchs.coupons.core.beans.GenericUser;
import com.galfuchs.coupons.core.dao.CompanyDao;
import com.galfuchs.coupons.core.dao.CouponDao;
import com.galfuchs.coupons.core.entities.CompanyEntity;
import com.galfuchs.coupons.core.exceptions.ApplicationException;
import com.galfuchs.coupons.core.validators.CompanyValidator;

@Controller
public class CompanyController {
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private CouponDao couponDao;
	
	@Autowired
	private CompanyValidator companyValidator;
	
	public CompanyController() {
		System.out.println("CompanyController created");
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public long createCompany(CompanyEntity company) throws ApplicationException {
		companyValidator.validateCompanyCreation(company);
		long id = companyDao.createCompany(company);
		return id;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public CompanyEntity getCompany(long companyId) throws ApplicationException {
		companyValidator.validateCompanyExists(companyId);
		CompanyEntity company = companyDao.getCompany(companyId);
		return company;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Collection<CompanyEntity> getAllCompanies() throws ApplicationException {
		Collection<CompanyEntity> allCompanies = new ArrayList<>();
		allCompanies = companyDao.getAllCompanies();
		for (CompanyEntity company: allCompanies) {
			company.setCoupons(couponDao.getAllCompanyCoupons(company));
		}
		return allCompanies;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateCompany(CompanyEntity company) throws ApplicationException {
		companyValidator.validateUpdateCompany(company);
		companyDao.updateCompany(company);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeCompany(long companyId) throws ApplicationException {
		companyValidator.validateCompanyExists(companyId);
		companyDao.removeCompany(companyId);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public long login(GenericUser user) throws ApplicationException {
		return companyDao.login(user);
	}
	


}
