package com.epam.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class GeneralFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(GeneralFilter.class);

	public GeneralFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
 			
			HttpSession session = ((HttpServletRequest) request).getSession(true);

			String language = (String) session.getAttribute("language");
			if (language == null) {
				LOG.info("language has not been specified in session");
				language = "en_US";
				LOG.info("english language has been applied");
				session.setAttribute("language", language);
			}
			chain.doFilter(request, response);
 	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
