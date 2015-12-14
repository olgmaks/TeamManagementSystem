package com.epam.web.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.bean.Order;
import com.epam.bean.User;
import com.epam.service.OrderService;
import com.epam.service.UserService;

public class UnconfirmedOrdersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(UnconfirmedOrdersServlet.class);
	private OrderService orderService;
	private UserService userService;

	public UnconfirmedOrdersServlet() {
		super();
		orderService = new OrderService();
		userService = new UserService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do get");

		List<Order> orders = orderService.getAllUncosidered();
		List<User> users = userService.getAll();

		request.setAttribute("orders", orders);
		request.setAttribute("users", users);
		request.getRequestDispatcher("/pages/unconfirmedorders.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("unconfirmed orders do post");
	}

}
