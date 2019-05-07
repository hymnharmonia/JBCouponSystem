package com.galfuchs.coupons.web.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResult = (HttpServletResponse) response;
		
		Cookie[] cookies = httpRequest.getCookies();

		boolean loggedIn = false;
		
		if (cookies != null) {
			for (Cookie cookie: cookies) {
				if (cookie.getName().equals("userLoggedIn") && cookie.getValue().equals("true")) loggedIn = true;
			}
		}
		
		String pageRequested=httpRequest.getRequestURL().toString();
		if (loggedIn == true || pageRequested.endsWith("/login")) {
			chain.doFilter(request, response);
			return;
		}	
		//if the session is null, we set the status of the request to unauthorized
		httpResult.setStatus(401);
//		httpResult.sendRedirect("https://www.youtube.com/watch?v=3xYXUeSmb-Y");
	}

	public void init(FilterConfig config) throws ServletException {}
	public void destroy() {}
}