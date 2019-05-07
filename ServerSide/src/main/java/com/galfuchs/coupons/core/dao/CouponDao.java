package com.galfuchs.coupons.core.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.galfuchs.coupons.core.entities.CompanyEntity;
import com.galfuchs.coupons.core.entities.CouponEntity;
import com.galfuchs.coupons.core.entities.CustomerEntity;
import com.galfuchs.coupons.core.enums.CouponType;
import com.galfuchs.coupons.core.exceptions.ApplicationException;

@Repository
public class CouponDao {

	@PersistenceContext(unitName="coupon_project")
	private EntityManager entityManager;

	public CouponDao() {
		System.out.println("CouponDao created");
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public long createCoupon(CouponEntity coupon) throws ApplicationException {
		entityManager.persist(coupon);
		return coupon.getId();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public CouponEntity getCoupon(long id) throws ApplicationException {
		CouponEntity coupon = entityManager.find(CouponEntity.class, id);
		return coupon;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeCoupon(long id) throws ApplicationException {
		CouponEntity coupon = getCoupon(id);
		entityManager.remove(coupon);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateCoupon(CouponEntity coupon) throws ApplicationException {
		entityManager.merge(coupon);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Collection<CouponEntity> getAllCoupons() throws ApplicationException {
		String hql = "FROM CouponEntity";
		Query query = entityManager.createQuery(hql);
		Collection<CouponEntity> allCoupons = query.getResultList();
		return allCoupons;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean checkExistingByTitle(String title) throws ApplicationException {
		 String hql = "FROM CouponEntity C WHERE C.title = :title";
			Query query = entityManager.createQuery(hql);
			query.setParameter("title", title);
			Collection<CouponEntity> result = query.getResultList();
			return !result.isEmpty() ? true : false;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean checkExistingById(long couponId) throws ApplicationException {
		String hql = "FROM CouponEntity C WHERE C.id = :id";
		Query query = entityManager.createQuery(hql);
		query.setParameter("id", couponId);
		Collection<CouponEntity> result = query.getResultList();
		return !result.isEmpty() ? true : false;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean checkIfCustomerPurchasedCoupon(long customerId, long couponId) throws ApplicationException {
		String hql = "FROM CouponEntity AS coupons INNER JOIN coupons.customers AS customer WHERE coupons.id = :couponId AND customer.id = :customerId";
		Query query = entityManager.createQuery(hql);
		query.setParameter("couponId", couponId);
		query.setParameter("customerId", customerId);
		Collection<CouponEntity> result = query.getResultList();
		return !result.isEmpty() ? true : false;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Collection<CouponEntity> getAllCustomerCoupons(long customerId) throws ApplicationException {
		String hql = "FROM CouponEntity AS coupons INNER JOIN coupons.customers AS customer WHERE customer.id = :customerId";
		Query query = entityManager.createQuery(hql);
		query.setParameter("customerId", customerId);
		Collection<CouponEntity> allCustomerCoupons = query.getResultList();
		return allCustomerCoupons;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Collection<CouponEntity> getCouponsByType(CouponType type) throws ApplicationException {
		String hql = "FROM CouponEntity C WHERE C.type = :couponType";
		Query query = entityManager.createQuery(hql);
		query.setParameter("couponType", type);
		Collection<CouponEntity> couponsByType = query.getResultList();
		return couponsByType;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Collection<CouponEntity> getAllCompanyCoupons(CompanyEntity company) throws ApplicationException {
		String hql = "FROM CouponEntity C WHERE C.company = :company";
		Query query = entityManager.createQuery(hql);
		query.setParameter("company", company);
		Collection<CouponEntity> allCompanyCoupons = query.getResultList();
		return allCompanyCoupons;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void purchaseCoupon(CouponEntity coupon, CustomerEntity customer) throws ApplicationException {
		coupon.getCustomers().add(customer);
		entityManager.merge(coupon);
	}
	
	
}
