package com.epam.web.servlet.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.service.OrderService;

public class DeleteOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(DeleteOrderServlet.class);
	private OrderService orderService;

	public DeleteOrderServlet() {
		super();
		orderService = new OrderService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do post");
		Integer orderId = Integer.valueOf(request
				.getParameter("orderidtobedeleted"));
		orderService.deleteOrderById(orderId);
		request.getRequestDispatcher("/customer").forward(request,
				response);
	}

}
