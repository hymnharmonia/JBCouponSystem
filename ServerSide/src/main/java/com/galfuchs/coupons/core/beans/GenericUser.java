package com.galfuchs.coupons.core.beans;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@XmlRootElement
@Component
public class GenericUser {
	
	private String name;
	private String email;
	private String password;
	private String clientType;
	private long id;
	private boolean isAdmin = false;
	
	public GenericUser() { }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setAdmin(boolean value) {
		this.isAdmin = value;
	}

}
