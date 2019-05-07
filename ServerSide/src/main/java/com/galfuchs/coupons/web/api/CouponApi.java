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

import com.galfuchs.coupons.core.entities.CouponEntity;
import com.galfuchs.coupons.core.enums.CouponType;
import com.galfuchs.coupons.core.exceptions.ApplicationException;
import com.galfuchs.coupons.core.logic.CouponController;

@RestController
@RequestMapping(value="/coupons")
public class CouponApi {
	
	@Autowired
	private CouponController couponController;

	@PostMapping
	public void createCoupon(@RequestBody CouponEntity coupon) throws ApplicationException {
		couponController.createCoupon(coupon);
	}
	
	@GetMapping
	public Collection<CouponEntity> getAllCoupons() throws ApplicationException {
		return couponController.getAllCoupons();
	}
	
	@GetMapping("/{couponId}")
	public CouponEntity getCoupon(@PathVariable long couponId) throws ApplicationException {
		return couponController.getCoupon(couponId);
	}
	
	@GetMapping("/byType/{couponType}")
	public Collection<CouponEntity> getCouponsByType(@PathVariable String couponType) throws ApplicationException {
		return couponController.getCouponsByType(CouponType.valueOf(couponType));
	}
	
	@PutMapping
	public void updateCoupon(@RequestBody CouponEntity coupon) throws ApplicationException {
		couponController.updateCoupon(coupon);
	}
	
	@DeleteMapping("/{couponId}")
	public void removeCoupon(@PathVariable long couponId) throws ApplicationException {
		couponController.removeCoupon(couponId);
	}
	
	@GetMapping("/byCompany/{companyId}")
	public Collection<CouponEntity> getCouponsByCompanyId(@PathVariable long companyId) throws ApplicationException {
		return couponController.getCouponsByCompanyId(companyId);
	}
	
	@PostMapping("/{couponId}/{customerId}")
	public void purchaseCoupon(@PathVariable long couponId, @PathVariable long customerId) throws ApplicationException {
		couponController.purchaseCoupon(couponId, customerId);
	}
	
	
}
