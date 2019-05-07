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
import com.galfuchs.coupons.core.entities.CompanyEntity;
import com.galfuchs.coupons.core.entities.CouponEntity;
import com.galfuchs.coupons.core.enums.ErrorType;
import com.galfuchs.coupons.core.exceptions.ApplicationException;

@Repository
public class CompanyDao {
	
	@PersistenceContext(unitName="coupon_project")
	private EntityManager entityManager;
	
	public CompanyDao() {
		System.out.println("CompanyDao created");
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public long createCompany(CompanyEntity companyEntity) throws ApplicationException {
		entityManager.persist(companyEntity);
		return companyEntity.getId();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public CompanyEntity getCompany(long id) throws ApplicationException {
		CompanyEntity company = entityManager.find(CompanyEntity.class, id);
		Query query = entityManager.createQuery("FROM CouponEntity C WHERE C.company.id=:companyId");
		query.setParameter("companyId",id);
		Collection<CouponEntity> companyCoupons = query.getResultList();
		company.setCoupons(companyCoupons);
		return company;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeCompany(long companyId) throws ApplicationException {
		CompanyEntity company = getCompany(companyId);
		entityManager.remove(company);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void updateCompany(CompanyEntity company) throws ApplicationException {
		entityManager.merge(company);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Collection<CompanyEntity> getAllCompanies() throws ApplicationException { 
		String hql = "FROM CompanyEntity";
		Query query = entityManager.createQuery(hql);
		Collection<CompanyEntity> allCompanies = query.getResultList();
		return allCompanies;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public boolean checkExistingByName(CompanyEntity company) throws ApplicationException {
		 String hql = "FROM CompanyEntity C WHERE C.companyName = :company_name";
			Query query = entityManager.createQuery(hql);
			query.setParameter("company_name", company.getCompanyName());
			Collection<CompanyEntity> result = query.getResultList();
			return !result.isEmpty() ? true : false;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public boolean checkExistingByCompanyId(long companyId) throws ApplicationException {
		 	CompanyEntity result = entityManager.find(CompanyEntity.class, companyId);
			return result == null  ? false : true;
	}

	
	@Transactional(propagation=Propagation.REQUIRED)
	public long login(GenericUser user) throws ApplicationException {
		String hql = "C.id FROM CompanyEntity C WHERE C.email = :company_email AND C.password = :company_password";
		Query query = entityManager.createQuery(hql);
		query.setParameter("company_email", user.getEmail());
		query.setParameter("company_password", user.getPassword());
		long loginId = -1;
		try {
			loginId = (long) query.getSingleResult();
		} catch (NoResultException e) {
			throw new ApplicationException(ErrorType.LOGIN_ERROR);
		}
		return loginId;
	}
}
