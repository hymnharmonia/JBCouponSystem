package com.galfuchs.coupons.core.beans;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.galfuchs.coupons.core.enums.CouponType;

@Component
@XmlRootElement
public class Coupon {
	
	/**
	 * Coupon bean, accepts id as long (generated by the db by default),
	 * start and end date as Java Date (formatted to 'dd-mm-yyyy' by the DAO), 
	 * amount as integer, type by the CouponType enum, message as string,
	 * price as double, and image as a URL in a string.
	 * 
	 * @author Gal Fuchs
	 * 
	 */

	private long id;
	private String title;
	private Date startDate;
	private Date endDate;
	private int amount;
	private CouponType type;
	private String message;
	private double price;
	private String image;
	private long companyId;
	
	public Coupon() {
		
	}

	public Coupon(String title, Date startDate, Date endDate, int amount, CouponType type, String message, double price,
			String image, long companyId) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
		this.companyId = companyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public CouponType getType() {
		return type;
	}

	public void setType(CouponType type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", type=" + type + ", message=" + message + ", price=" + price + ", image="
				+ image + ", companyId=" + companyId + "]";
	}


}
