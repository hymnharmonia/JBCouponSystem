package com.galfuchs.coupons.core.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@Component
public class Company {

	private long id;
	private String compName;
	private String password;
	private String email;
	private List<Coupon> coupons = new ArrayList<>();
	
	
	/**
	 * Company bean, accepts three strings for Company Name, Password and E-mail address
	 * Includes standard getters and setters
	 */
	public Company() {
		// Empty Company constructor for DBDAO
	}
	public Company(String compName, String password, String email) {
		super();
		this.compName = compName;
		this.password = password;
		this.email = email;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String toString() {
		return "Company [id=" + this.id + ", name=" + this.compName + ", password=" + this.password + ", email="
				+ this.email + ", coupons=" + this.coupons + "]";
	}

}
