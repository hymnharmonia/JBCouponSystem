package com.galfuchs.coupons.core.logic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.galfuchs.coupons.core.dao.CompanyDao;
import com.galfuchs.coupons.core.dao.CouponDao;
import com.galfuchs.coupons.core.dao.CustomerDao;
import com.galfuchs.coupons.core.entities.CompanyEntity;
import com.galfuchs.coupons.core.entities.CouponEntity;
import com.galfuchs.coupons.core.entities.CustomerEntity;
import com.galfuchs.coupons.core.enums.CouponType;
import com.galfuchs.coupons.core.exceptions.ApplicationException;
import com.galfuchs.coupons.core.validators.CouponValidator;

@Controller
public class CouponController {
	
	@Autowired
	private CouponValidator couponValidator;
	
	@Autowired
	private CouponDao couponDao;
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public long createCoupon(CouponEntity coupon) throws ApplicationException {
		couponValidator.couponIsValid(coupon);
		return couponDao.createCoupon(coupon);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeCoupon(long couponId) throws ApplicationException {
		if (couponValidator.validateCouponExists(couponId)) {
		couponDao.removeCoupon(couponId);
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateCoupon(CouponEntity coupon) throws ApplicationException {
		if (couponValidator.couponUpdateIsValid(coupon)) {
			couponDao.updateCoupon(coupon);
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public CouponEntity getCoupon(long couponId) throws ApplicationException {
		couponValidator.validateCouponExists(couponId);
		return couponDao.getCoupon(couponId);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Collection<CouponEntity> getAllCoupons() throws ApplicationException {
		return couponDao.getAllCoupons();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Collection<CouponEntity> getCouponsByType(CouponType type) throws ApplicationException {
		return couponDao.getCouponsByType(type);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Collection<CouponEntity> getCouponsByCompanyId(long companyId) throws ApplicationException {
		CompanyEntity company = companyDao.getCompany(companyId);
		return couponDao.getAllCompanyCoupons(company);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void purchaseCoupon(long couponId, long customerId) throws ApplicationException {
		couponValidator.checkIfCouponPurchasable(customerId, couponId);
		CouponEntity coupon = couponDao.getCoupon(couponId);
		CustomerEntity customer = customerDao.getCustomer(customerId);
		couponDao.purchaseCoupon(coupon, customer);
		coupon.setAmount(coupon.getAmount() - 1);
		couponDao.updateCoupon(coupon);
	}
	
}
