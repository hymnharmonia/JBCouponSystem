package com.galfuchs.coupons.core.logic;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.galfuchs.coupons.core.beans.GenericUser;
import com.galfuchs.coupons.core.dao.CouponDao;
import com.galfuchs.coupons.core.dao.CustomerDao;
import com.galfuchs.coupons.core.entities.CouponEntity;
import com.galfuchs.coupons.core.entities.CustomerEntity;
import com.galfuchs.coupons.core.exceptions.ApplicationException;
import com.galfuchs.coupons.core.validators.CustomerValidator;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerValidator customerValidator;
	
	@Autowired
	private CouponDao couponDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	public void createCustomer(CustomerEntity customer) throws ApplicationException {
		customerValidator.validateCustomerCreation(customer);
		customerDao.createCustomer(customer);
	}
	
	public CustomerEntity getCustomer(long customerId) throws ApplicationException {
		customerValidator.validateCustomerExists(customerId);
		return customerDao.getCustomer(customerId);
	}
	
	public Collection<CustomerEntity> getAllCustomers() throws ApplicationException {
		return customerDao.getAllCustomers();
	}
	
	public void updateCustomer(CustomerEntity customer) throws ApplicationException {
		customerValidator.validateCustomerUpdate(customer);
		customerDao.updateCustomer(customer);
	}
	
	public void removeCustomer(long customerId) throws ApplicationException {
		customerValidator.validateCustomerExists(customerId);
		CustomerEntity customer = getCustomer(customerId);
		Collection<CouponEntity> allCustomerCoupons = couponDao.getAllCustomerCoupons(customerId);
		for (CouponEntity coupon: allCustomerCoupons) {
			coupon.getCustomers().remove(customer);
			couponDao.updateCoupon(coupon);
		}
		customerDao.removeCustomer(customerId);
	}
	
	public long login(GenericUser user) throws ApplicationException {
		return customerDao.login(user);
	}
}
