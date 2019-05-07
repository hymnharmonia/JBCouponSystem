package com.galfuchs.coupons.core.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.galfuchs.coupons.core.dao.CustomerDao;
import com.galfuchs.coupons.core.entities.CustomerEntity;
import com.galfuchs.coupons.core.enums.ErrorType;
import com.galfuchs.coupons.core.exceptions.ApplicationException;

@Component
public class CustomerValidator {
	
	@Autowired
	private CredentialsValidator credentialsValidator;
	
	@Autowired
	private CustomerDao customerDao;
	
	public void validateCustomerCreation(CustomerEntity customer) throws ApplicationException {
		if (customerDao.checkExistingByName(customer.getName())) {
			throw new ApplicationException(ErrorType.CUSTOMER_EXISTS);
		}
		credentialsValidator.validateCustomerCredentials(customer);
	}
	
	public void validateCustomerExists(long customerId) throws ApplicationException {
		if (!customerDao.checkExistingById(customerId)) {
			throw new ApplicationException(ErrorType.CUSTOMER_DOES_NOT_EXIST);
		}
	}
	
	public void validateCustomerUpdate(CustomerEntity customer) throws ApplicationException {
		if (!customerDao.checkExistingByName(customer.getName())) {
			throw new ApplicationException(ErrorType.CUSTOMER_DOES_NOT_EXIST);
		}
		credentialsValidator.validateCustomerCredentials(customer);		
	}
}
