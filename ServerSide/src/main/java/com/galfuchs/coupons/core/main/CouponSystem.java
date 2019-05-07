package com.galfuchs.coupons.core.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.galfuchs.coupons.core.utils.DailyCouponExpirationTask;

@Component
public class CouponSystem {
	
	@Autowired
	private DailyCouponExpirationTask dailyCouponExpirationTask;
	
	private Thread dailyCouponExpirationThread = new Thread(
			dailyCouponExpirationTask);

	private CouponSystem() {
		System.out.println("Why twice?");
		dailyCouponExpirationThread.setDaemon(true);
		dailyCouponExpirationThread.start();
		System.out.println("Daily thread started");
	}
	
	public void shutdown() {
		dailyCouponExpirationTask.stop();
		dailyCouponExpirationThread.interrupt();
		try {
			dailyCouponExpirationThread.join();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Shutting down...");
		System.exit(0);
	}

}
