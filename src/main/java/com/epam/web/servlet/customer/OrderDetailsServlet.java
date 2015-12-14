package com.epam.web.servlet.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.bean.Task;
import com.epam.bean.TaskLoad;
import com.epam.service.TaskLoadService;
import com.epam.service.TaskService;

public class OrderDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(OrderDetailsServlet.class);
	private TaskService taskService;
	private TaskLoadService taskLoadService;

	public OrderDetailsServlet() {
		super();
		taskService = new TaskService();
		taskLoadService = new TaskLoadService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do get");
		int orderId = Integer.valueOf(request.getParameter("orderid"));
		request.getSession(true).setAttribute("orderid", orderId);
		List<Task> tasks = taskService.getAll();
		List<TaskLoad> taskLoads = taskLoadService
				.getAllLoadTasksByOrderId(orderId);

		request.setAttribute("tasks", tasks);
		request.setAttribute("taskloads", taskLoads);
		request.getRequestDispatcher("pages/orderdetails.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
