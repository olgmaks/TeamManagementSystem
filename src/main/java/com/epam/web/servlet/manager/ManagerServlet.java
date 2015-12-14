package com.epam.web.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.bean.Order;
import com.epam.bean.Project;
import com.epam.service.OrderService;
import com.epam.service.ProjectService;

public class ManagerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ManagerServlet.class);
	private OrderService orderService;
	private ProjectService projectService;

	public ManagerServlet() {
		super();
		orderService = new OrderService();
		projectService = new ProjectService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info(" do get");
		
		List<Order> orders = orderService.getAll();
		List<Project> projects = projectService.getAll();

		request.setAttribute("orders", orders);
		request.setAttribute("projects", projects);
		
		request.getRequestDispatcher("/pages/managerpage.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do post");
		doGet(request, response);
	}

}
