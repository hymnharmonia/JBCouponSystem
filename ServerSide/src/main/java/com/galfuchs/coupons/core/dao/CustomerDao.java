package com.galfuchs.coupons.core.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.galfuchs.coupons.core.beans.GenericUser;
import com.galfuchs.coupons.core.entities.CustomerEntity;
import com.galfuchs.coupons.core.enums.ErrorType;
import com.galfuchs.coupons.core.exceptions.ApplicationException;

@Repository
public class CustomerDao {

	@PersistenceContext(unitName = "coupon_project")
	private EntityManager entityManager;

	public CustomerDao() {
		System.out.println("CustomerDao created");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public long createCustomer(CustomerEntity customer) throws ApplicationException {
		entityManager.persist(customer);
		return customer.getId();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removeCustomer(long customerId) throws ApplicationException {
		CustomerEntity customer = getCustomer(customerId);
		entityManager.remove(customer);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateCustomer(CustomerEntity customer) throws ApplicationException {
		entityManager.merge(customer);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CustomerEntity getCustomer(long id) throws ApplicationException {
		CustomerEntity customer = entityManager.find(CustomerEntity.class, id);
//		String hql = "FROM CouponEntity C JOIN C.customers Cust WHERE Cust.id=:id";
//		Query query = entityManager.createQuery(hql).setParameter("id", id);
//		Collection<CouponEntity> coupons = query.getResultList();
//		customer.setCoupons(coupons);
		return customer;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Collection<CustomerEntity> getAllCustomers() throws ApplicationException {
		String hql = "FROM CustomerEntity";
		Query query = entityManager.createQuery(hql);
		Collection<CustomerEntity> allCustomers = query.getResultList();
		return allCustomers;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean checkExistingByName(String name) throws ApplicationException {
		String hql = "FROM CustomerEntity C WHERE C.name = :name";
		Query query = entityManager.createQuery(hql);
		query.setParameter("name", name);
		Collection<CustomerEntity> result = query.getResultList();
		return !result.isEmpty() ? true : false;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean checkExistingById(long customerId) throws ApplicationException {
		String hql = "FROM CustomerEntity C WHERE C.id = :customerId";
		Query query = entityManager.createQuery(hql);
		query.setParameter("customerId", customerId);
		Collection<CustomerEntity> result = query.getResultList();
		return !result.isEmpty() ? true : false;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public long login(GenericUser user) throws ApplicationException {
		String hql = "C.id FROM CustomerEntity C WHERE C.name = :customerName AND C.password = :customerPassword";
		Query query = entityManager.createQuery(hql);
		query.setParameter("customerName", user.getName());
		query.setParameter("customerPassword", user.getPassword());
		long loginId = -1;
		try {
			loginId = (long) query.getSingleResult();
		} catch (NoResultException e) {
			throw new ApplicationException(ErrorType.LOGIN_ERROR);
		}
		return loginId;
	}

}
