package com.galfuchs.coupons.core.utils;

import java.sql.Date;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.galfuchs.coupons.core.dao.CouponDao;
import com.galfuchs.coupons.core.entities.CouponEntity;
import com.galfuchs.coupons.core.exceptions.ApplicationException;

@Component
public class DailyCouponExpirationTask implements Runnable {

	private boolean quit = false;
	private final long DAY_IN_MILLISECONDS = 86_400_000;

	@Autowired
	private CouponDao couponDao;

	public DailyCouponExpirationTask() {
	}

	@Override
	public void run() {
		try {
			while (!quit) {
				Date todaysDate = new Date(System.currentTimeMillis());
				Collection<CouponEntity> allCoupons = couponDao.getAllCoupons();
				if (allCoupons != null) {
					for (CouponEntity coupon : allCoupons) {
						if (!quit) {
							if (DateUtils.stringDateToSqlDate(coupon.getEnd_date()).before(todaysDate)) {
								couponDao.removeCoupon(coupon.getId());
							}
						} else {
							return;
						}
					}
				}
				Thread.sleep(DAY_IN_MILLISECONDS);
			}
		} catch (InterruptedException e) {
			System.err.println("Daily task interrupted");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		quit = true;
	}
}
