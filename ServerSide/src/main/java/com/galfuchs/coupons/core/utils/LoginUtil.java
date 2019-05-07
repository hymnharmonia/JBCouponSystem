package com.galfuchs.coupons.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.galfuchs.coupons.core.beans.GenericUser;
import com.galfuchs.coupons.core.exceptions.ApplicationException;
import com.galfuchs.coupons.core.logic.CompanyController;
import com.galfuchs.coupons.core.logic.CustomerController;

@Controller
public class LoginUtil {
	
	@Autowired
	private CompanyController companyController;
	
	@Autowired
	private CustomerController customerController;
	
	public GenericUser login(GenericUser user) throws ApplicationException {
		switch (user.getClientType()) {
		case "Company":
			user.setId(companyController.login(user));
			return user;
		case "Customer":
			user.setId(customerController.login(user));
			return user;
		default:
			if (user.getEmail().equals("admin@admin.com") && user.getPassword().equals("admin")) {
				user.setAdmin(true);
			}
			return user;
		}
	}

}
