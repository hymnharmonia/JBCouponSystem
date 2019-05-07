package com.galfuchs.coupons.core.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.galfuchs.coupons.core.dao.CompanyDao;
import com.galfuchs.coupons.core.entities.CompanyEntity;
import com.galfuchs.coupons.core.enums.ErrorType;
import com.galfuchs.coupons.core.exceptions.ApplicationException;

@Component
public class CompanyValidator {
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private CredentialsValidator credentialsValidator;
	
	public void validateCompanyCreation(CompanyEntity company) throws ApplicationException {
		if (companyDao.checkExistingByName(company)) {
			throw new ApplicationException(ErrorType.COMPANY_EXISTS);
		}
		credentialsValidator.validateCompanyCredentials(company);
	}
	
	public boolean validateCompanyExists(long companyId) throws ApplicationException {
		if (!companyDao.checkExistingByCompanyId(companyId)) {
			throw new ApplicationException(ErrorType.COMPANY_DOES_NOT_EXIST);
		}
		return true;
	}

	public void validateUpdateCompany(CompanyEntity company) throws ApplicationException {
		if (!companyDao.checkExistingByCompanyId(company.getId())) {
			throw new ApplicationException(ErrorType.COMPANY_DOES_NOT_EXIST);
		}
		credentialsValidator.validateCompanyCredentials(company);
	}

}
