package com.galfuchs.coupons.core.validators;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.galfuchs.coupons.core.dao.CompanyDao;
import com.galfuchs.coupons.core.dao.CouponDao;
import com.galfuchs.coupons.core.entities.CouponEntity;
import com.galfuchs.coupons.core.enums.ErrorType;
import com.galfuchs.coupons.core.exceptions.ApplicationException;
import com.galfuchs.coupons.core.utils.DateUtils;

@Component
public class CouponValidator {
	
	@Autowired
	private CouponDao couponDao;
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private CustomerValidator customerValidator;
	
	public boolean couponIsValid(CouponEntity coupon) throws ApplicationException {
		if (couponDao.checkExistingByTitle(coupon.getTitle())) {
			throw new ApplicationException(ErrorType.COUPON_EXISTS);
		} else if (!endDateIsValid(coupon)) {
			throw new ApplicationException(ErrorType.COUPON_END_DATE_INVALID);
		} else if (!companyDao.checkExistingByCompanyId(coupon.getCompany().getId())) {
			throw new ApplicationException(ErrorType.COMPANY_DOES_NOT_EXIST);
		}
		amountIsValid(coupon);
		priceIsValid(coupon);
		return true;
	}
	
	public boolean couponUpdateIsValid(CouponEntity coupon) throws ApplicationException {
		if (coupon.getId() == 0) {
			throw new ApplicationException(ErrorType.SYSTEM_ERROR, "No coupon ID supplied");
		}
		if (!couponDao.checkExistingByTitle(coupon.getTitle())) {
			throw new ApplicationException(ErrorType.COUPON_DOES_NOT_EXIST);
		} else if (!endDateIsValid(coupon)) {
			throw new ApplicationException(ErrorType.COUPON_END_DATE_INVALID);
		} else if (!companyDao.checkExistingByCompanyId(coupon.getCompany().getId())) {
			throw new ApplicationException(ErrorType.COMPANY_DOES_NOT_EXIST);
		}
		amountIsValid(coupon);
		priceIsValid(coupon);
		return true;
	}

	public boolean endDateIsValid(CouponEntity coupon) throws ApplicationException {
		Date currentDate = new Date(System.currentTimeMillis());
		if (DateUtils.stringDateToSqlDate(coupon.getEnd_date()).after(currentDate)) {
			return true;
		}
		return false;
	}
	
	public boolean validateCouponExists(long couponId) throws ApplicationException {
		if (!couponDao.checkExistingById(couponId)) {
			throw new ApplicationException(ErrorType.COUPON_DOES_NOT_EXIST);
		}
		return true;
	}
	
	public boolean checkIfCouponPurchasable(long couponId, long customerId) throws ApplicationException {
		validateCouponExists(couponId);
		customerValidator.validateCustomerExists(customerId);
		CouponEntity coupon = couponDao.getCoupon(couponId);
		Date currentDate = new Date(System.currentTimeMillis());
		if (couponDao.checkIfCustomerPurchasedCoupon(customerId, couponId)) {
			throw new ApplicationException(ErrorType.CUSTOMER_ALREADY_OWNS_COUPON);
		}
		else if (coupon.getAmount() < 1) {
			throw new ApplicationException(ErrorType.COUPON_NOT_IN_STOCK);
		} else if (DateUtils.stringDateToSqlDate(coupon.getEnd_date()).before(currentDate)) {
			throw new ApplicationException(ErrorType.COUPON_END_DATE_INVALID);
		}
		return true;
	}
	
	private boolean amountIsValid(CouponEntity coupon) throws ApplicationException {
		if (coupon.getAmount() >= 0) {
			return true;
		} else {
			throw new ApplicationException(ErrorType.COUPON_AMOUNT_INVALID);
		}
	}
	
	private boolean priceIsValid(CouponEntity coupon) throws ApplicationException {
		if (coupon.getPrice() >= 0) {
			return true;
		} else {
			throw new ApplicationException(ErrorType.COUPON_PRICE_INVALID);
		}
	}
	
}
