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

import com.galfuchs.coupons.core.entities.CustomerEntity;
import com.galfuchs.coupons.core.exceptions.ApplicationException;
import com.galfuchs.coupons.core.logic.CustomerController;

@RestController
@RequestMapping(value="/customers")
public class CustomerApi {
	
	@Autowired
	private CustomerController customerController;
	
	@PostMapping
	public void createCustomer(@RequestBody CustomerEntity customer) throws ApplicationException {
		customerController.createCustomer(customer);
	}
	
	@GetMapping
	public Collection<CustomerEntity> getAllCustomers() throws ApplicationException {
		return customerController.getAllCustomers();
	}
	
	@GetMapping("/{customerId}")
	public CustomerEntity getCustomer(@PathVariable long customerId) throws ApplicationException {
		return customerController.getCustomer(customerId);
	}
	
	@PutMapping
	public void updateCustomer(@RequestBody CustomerEntity customer) throws ApplicationException {
		customerController.updateCustomer(customer);
	}
	
	@DeleteMapping("/{customerId")
	public void removeCustomer(@PathVariable long customerId) throws ApplicationException {
		customerController.removeCustomer(customerId);
	}

}
