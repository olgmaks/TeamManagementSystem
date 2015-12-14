package com.epam.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.bean.User;
import com.epam.web.servlet.login.LoginServlet;

public class CustomerFilter implements Filter {

	public CustomerFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();

		if (session != null) {
			User sessionUser = (User) session
					.getAttribute(LoginServlet.SESSION_USER);
			if (sessionUser != null && sessionUser.getRole().equals("customer")) {
				chain.doFilter(request, response);
				return;
			}
		}
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendRedirect("");

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
