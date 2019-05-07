package com.galfuchs.coupons.core.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.galfuchs.coupons.core.entities.CompanyEntity;
import com.galfuchs.coupons.core.entities.CustomerEntity;
import com.galfuchs.coupons.core.enums.ErrorType;
import com.galfuchs.coupons.core.exceptions.ApplicationException;

@Component
public class CredentialsValidator {
	
	public boolean validateCompanyCredentials(CompanyEntity company) throws ApplicationException {
		if (!validateEmail(company.getEmail())) throw new ApplicationException(ErrorType.INVALID_EMAIL);
		if (!validatePassword(company.getPassword())) throw new ApplicationException(ErrorType.INVALID_PASSWORD);
		return true;
	}
	
	public boolean validateCustomerCredentials(CustomerEntity customer) throws ApplicationException {
		if (!validatePassword(customer.getPassword())) throw new ApplicationException(ErrorType.INVALID_PASSWORD);
		return true;
	}
	

	private boolean validateEmail(String email) {
		Pattern validEmailRegex = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}\\.[a-z]{2,}$");
		Matcher emailMatcher = validEmailRegex.matcher(email);
		if (emailMatcher.matches()) {
			return true;
		}
		return false;
	}
	
	private boolean validatePassword(String password){
		Pattern validPasswordRegex = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$");
		Matcher passwordMatcher = validPasswordRegex.matcher(password);
		if (passwordMatcher.matches()) {
			return true;
		}
		return false;
	}

}
