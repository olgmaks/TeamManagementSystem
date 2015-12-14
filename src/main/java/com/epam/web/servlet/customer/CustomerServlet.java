package com.epam.web.servlet.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.bean.Order;
import com.epam.bean.Project;
import com.epam.bean.User;
import com.epam.service.OrderService;
import com.epam.service.ProjectService;
import com.epam.web.servlet.login.LoginServlet;

public class CustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(CustomerServlet.class);
	private OrderService orderService;
	private ProjectService projectService;

	public CustomerServlet() {
		super();
		orderService = new OrderService();
		projectService = new ProjectService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do get");

		User sessionUser = (User) request.getSession(true).getAttribute(
				LoginServlet.SESSION_USER);

		List<Order> orders = orderService.getAllOrdersByCustomerId(sessionUser
				.getId());
		List<Project> projects = projectService.getAll();

		List<Order> unconsideredOrders = orderService
				.getAllUncosideredByCustomerId(sessionUser.getId());

		request.setAttribute("orders", orders);
		request.setAttribute("unconsideredorders", unconsideredOrders);
		request.setAttribute("projects", projects);

		LOG.info("try to send on customer page");
		request.getRequestDispatcher("/pages/customerpage.jsp").forward(
				request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do post");
		doGet(request, response);
	}

}
