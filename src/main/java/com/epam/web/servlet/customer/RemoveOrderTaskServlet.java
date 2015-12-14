package com.epam.web.servlet.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.service.TaskLoadService;

public class RemoveOrderTaskServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(RemoveOrderTaskServlet.class);
	private TaskLoadService taskLoadService;

	public RemoveOrderTaskServlet() {
		super();
		taskLoadService = new TaskLoadService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do get");
		int orderId = (int) request.getSession(true).getAttribute("orderid");
		taskLoadService.deleteTaskLoad(Integer.valueOf(request
				.getParameter("taskloadid")));
		LOG.info("deleting element task with id = "
				+ request.getParameter("taskloadid")
				+ " from order where id = " + orderId);
		request.getRequestDispatcher("/orderdetails?orderid=" + orderId)
				.forward(request, response);
		;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
