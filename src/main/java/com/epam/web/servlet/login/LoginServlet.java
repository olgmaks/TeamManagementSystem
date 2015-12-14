package com.epam.web.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.bean.User;
import com.epam.service.UserService;

public class LoginServlet extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(LoginServlet.class);
	public static final String USER_INFO_TAB_REF = "userinfotab";
	public static final String VALIDATION_CONDITION_REF = "usernamevalidationcondition";
	public static final String ERROR_MESSAGE_REF = "errormessage";
	public static final String EMAIL_FILLING_REF = "emailfield";

	public static final String SESSION_USER = "user";
	public static final String ERROR_MESSAGE_CONTENT = "incorrect login or/and password";

	public static final String EMAIL_INPUT = "e-mail";
	public static final String PASSWORD_INPUT = "password";

	private static final long serialVersionUID = 1L;

	private UserService userService;

	public LoginServlet() {
		super();
		userService = new UserService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do get");
		request.setCharacterEncoding("UTF-8");
		User sessionUser = (User) request.getSession().getAttribute(
				SESSION_USER);

		if (sessionUser == null) {
			request.setAttribute(VALIDATION_CONDITION_REF, true);
		} else {
			request.setAttribute("username", sessionUser.getFirstName());
		}

		request.getRequestDispatcher("/pages/index.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do post");
		String email = request.getParameter(EMAIL_INPUT);
		String pass = request.getParameter(PASSWORD_INPUT);
		HttpSession session = request.getSession(true);

		for (User user : userService.getAll()) {
			if (email.equals(user.getEmail())
					&& pass.equals(user.getPassword())) {
				User currentUser = userService.getUserByEmail(email);
				request.setAttribute(VALIDATION_CONDITION_REF, false);
				session.setAttribute(SESSION_USER, currentUser);
				session.setAttribute("indexhomeref", currentUser.getRole());
				session.setAttribute("loggingimage", "img/icons/loginlocked.png");
				
				
				LOG.info("user : " + email);
				break;
			}
		}

		/**
		 * Login failed (Show error message
		 */
		if (request.getSession().getAttribute(SESSION_USER) == null) {
			request.setAttribute(ERROR_MESSAGE_REF, ERROR_MESSAGE_CONTENT);
			request.setAttribute(EMAIL_FILLING_REF, email);
			LOG.info("no user registrated in session");
			doGet(request, response);
		} else {
			User sessionUser = (User) request.getSession(true).getAttribute(
					SESSION_USER);
			session.setAttribute("usernamevalidationcondition", true);
			LOG.info("user role : " + sessionUser.getRole());
			if (sessionUser.getRole().equals("admin")) {
				LOG.info("admin access");
				request.getRequestDispatcher("/admin").forward(request,
						response);
			} else if (sessionUser.getRole().equals("customer")) {
				LOG.info("customer access");
				request.getRequestDispatcher("/customer").forward(request,
						response);
			} else if (sessionUser.getRole().equals("manager")) {
				LOG.info("manager access");
				request.getRequestDispatcher("/manager").forward(request,
						response);
			} else if (sessionUser.getRole().equals("developer")) {
				LOG.info("developer access");
				request.getRequestDispatcher("/developer").forward(request,
						response);
			}
		}

	}

}
