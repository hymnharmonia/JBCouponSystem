package com.galfuchs.coupons.core.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.galfuchs.coupons.core.enums.CouponType;

@Entity
@Table(name="coupon")
public class CouponEntity {
	
	@GeneratedValue
	@Id
	private long id;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="start_date", nullable=false)
	private String start_date;
	
	@Column(name="end_date", nullable=false)
	private String end_date;
	
	@Column(name="amount", nullable=false)
	private int amount;
	
	@Column(name="type", nullable=false)
	private CouponType type;
	
	@Column(name="message", nullable=false)
	private String message;
	
	@Column(name="price", nullable=false)
	private double price;
	
	@Column(name="image", nullable=false)
	private String image;
	
	@ManyToOne
	@JsonBackReference
	private CompanyEntity company;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JsonIgnore
	@JoinTable(name="customer_coupon", joinColumns = {@JoinColumn(name="coup_id")}, inverseJoinColumns = {@JoinColumn(name="cust_id")})
	private Collection<CustomerEntity> customers;

	public CouponEntity() {}
	
	public CouponEntity(String title, String start_date, String end_date, int amount, CouponType type, String message,
			double price, String image, CompanyEntity company, Collection<CustomerEntity> customers) {
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
		this.company = company;
		this.customers = customers;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CouponEntity other = (CouponEntity) obj;
		if (amount != other.amount)
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (customers == null) {
			if (other.customers != null)
				return false;
		} else if (!customers.equals(other.customers))
			return false;
		if (end_date == null) {
			if (other.end_date != null)
				return false;
		} else if (!end_date.equals(other.end_date))
			return false;
		if (id != other.id)
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	public int getAmount() {
		return amount;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public Collection<CustomerEntity> getCustomers() {
		return customers;
	}

	public String getEnd_date() {
		return end_date;
	}

	public long getId() {
		return id;
	}

	public String getImage() {
		return image;
	}

	public String getMessage() {
		return message;
	}

	public double getPrice() {
		return price;
	}

	public String getStart_date() {
		return start_date;
	}

	public String getTitle() {
		return title;
	}

	public CouponType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((customers == null) ? 0 : customers.hashCode());
		result = prime * result + ((end_date == null) ? 0 : end_date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((start_date == null) ? 0 : start_date.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

	public void setCustomers(Collection<CustomerEntity> customers) {
		this.customers = customers;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(CouponType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CouponEntity [id=" + id + ", title=" + title + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", amount=" + amount + ", type=" + type + ", message=" + message + ", price=" + price + ", image="
				+ image + ", company=" + company + ", customers=" + customers + "]";
	}
	
}
