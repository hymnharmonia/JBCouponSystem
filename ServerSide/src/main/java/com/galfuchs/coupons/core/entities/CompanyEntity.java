package com.galfuchs.coupons.core.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="company")
public class CompanyEntity {
	
	@GeneratedValue
	@Id
	private long id;
	
	@Column(name="companyName", nullable=false)
	private String companyName;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@OneToMany(cascade = {CascadeType.ALL},mappedBy ="company", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Collection<CouponEntity> coupons;
	
	public CompanyEntity() {}
	
	public CompanyEntity(String companyName, String password, String email) {
		this.companyName = companyName;
		this.password = password;
		this.email = email;
	}
	
	public CompanyEntity(String companyName, String password, String email, Collection<CouponEntity> coupons) {
		super();
		this.companyName = companyName;
		this.password = password;
		this.email = email;
		this.coupons = coupons;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Collection<CouponEntity> getCoupons() {
		return coupons;
	}

	public String getEmail() {
		return email;
	}

	public long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCoupons(Collection<CouponEntity> coupons) {
		this.coupons = coupons;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyEntity other = (CompanyEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CompanyEntity [id=" + id + ", companyName=" + companyName + ", password=" + password + ", email="
				+ email + ", coupons=" + coupons + "]";
	}
	
}
