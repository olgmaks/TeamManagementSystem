package com.epam.web.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.bean.User;
import com.epam.service.UserService;

public class UserRegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(UserRegistrationServlet.class);
	
	private UserService userService;

	public UserRegistrationServlet() {
		super();
		userService = new UserService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do get");
		request.setAttribute("invalidinput", false);
		request.getRequestDispatcher("pages/registrationpage.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do post");
		request.setCharacterEncoding("UTF-8");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String sex = request.getParameter("sex");
		String email = request.getParameter("e-mail");
		String password = request.getParameter("password");

		if (!firstName.isEmpty() && !lastName.isEmpty() && !sex.isEmpty()
				&& !email.isEmpty() && !password.isEmpty()) {
			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPassword(password);
			user.setSex(sex);
			user.setRole("customer");
			userService.saveUser(user);
			request.getSession(true).setAttribute(LoginServlet.SESSION_USER,
					userService.getUserByEmail(email));
			request.setAttribute("users", userService.getAll());
			request.getRequestDispatcher("/login").include(request, response);
		} else {
			request.setAttribute("invalidinput", true);
			request.getRequestDispatcher("pages/registrationpage.jsp").forward(request, response);
		}

	}
}
