package com.epam.web.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.bean.Developer;
import com.epam.bean.User;
import com.epam.service.DeveloperService;
import com.epam.service.UserService;

public class DevelopersInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(DevelopersInfoServlet.class);
	
	private DeveloperService developerService;
	private UserService userService;

	public DevelopersInfoServlet() {
		super();
		developerService = new DeveloperService();
		userService = new UserService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("developers info do get");
		List<Developer> developers = developerService.getAll();
		List<User> users = userService.getAll();
		request.setAttribute("developers", developers);
		request.setAttribute("users", users);
		request.getRequestDispatcher("/pages/developersinfo.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
