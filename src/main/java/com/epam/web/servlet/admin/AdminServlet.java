package com.epam.web.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.bean.User;
import com.epam.service.UserService;

public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AdminServlet.class);
	
	private UserService userService;

	public AdminServlet() {
		super();
		userService = new UserService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do get");
		List<User> users = userService.getAll();
		request.setAttribute("users", users);
		request.getRequestDispatcher("pages/userinfo.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do post");
		doGet(request, response);
	}

}
