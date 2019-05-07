package com.galfuchs.coupons.web.api;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.galfuchs.coupons.core.beans.GenericUser;
import com.galfuchs.coupons.core.enums.ErrorType;
import com.galfuchs.coupons.core.exceptions.ApplicationException;
import com.galfuchs.coupons.core.utils.LoginUtil;

@RequestMapping(value="")
public class LoginApi {
	
	@Autowired
	private LoginUtil loginUtil;
	
	private HttpServletResponse response;

	@PostMapping
	public GenericUser login(@RequestBody GenericUser user) throws ApplicationException {
		
		Cookie cookieUserLoggedIn;
		GenericUser loggedInUser = loginUtil.login(user);
		
		if(loggedInUser.getId() != -1) {
			cookieUserLoggedIn = new Cookie("userLoggedIn", "true");
			System.out.println(String.format("> [%s] User %s %s logged in", new Date(), user.getClientType(), loggedInUser.getId()));
			cookieUserLoggedIn.setMaxAge(-1);
			response.addCookie(cookieUserLoggedIn);
			return loggedInUser;
		}
		throw new ApplicationException(ErrorType.LOGIN_ERROR);
	}
	
	public void logout() {
		Cookie cookieUserLoggedIn = new Cookie("userLoggedIn","null");
		cookieUserLoggedIn.setMaxAge(0);
		response.addCookie(cookieUserLoggedIn);
		System.out.println(String.format("> [%s] User logged out", new Date()));
	}

}
