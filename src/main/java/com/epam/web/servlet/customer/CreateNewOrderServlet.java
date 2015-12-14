package com.epam.web.servlet.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.bean.Order;
import com.epam.bean.Task;
import com.epam.bean.TaskLoad;
import com.epam.bean.User;
import com.epam.lab.dbutils.ConnectionManager;
import com.epam.service.OrderService;
import com.epam.service.TaskLoadService;
import com.epam.service.TaskService;
import com.epam.web.servlet.login.LoginServlet;

public class CreateNewOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(CreateNewOrderServlet.class);
	private TaskService taskService;
	private TaskLoadService taskLoadService;
	private OrderService orderService;

	public CreateNewOrderServlet() {
		super();
		taskService = new TaskService();
		taskLoadService = new TaskLoadService();
		orderService = new OrderService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do get");
		String orderName = request.getParameter("ordername");
		String orderDescription = request.getParameter("orderdescription");
		String orderAdditionalInfo = request
				.getParameter("orderadditionalinfo");
		
		
		if (!orderName.isEmpty()) {
			
			//Verifying for similar names
			for (Order  order : orderService.getAll()) {
				if (order.getName().equals(orderName)) {
					LOG.info("dublicating of order names");
					orderName+=" " + (new Date());
				}
			}
					
			
			System.out.println("create new order do get with get form");
			Order order = new Order();
			order.setName(orderName);
			order.setDescription(orderDescription);
			order.setAdditionalInformation(orderAdditionalInfo);
			order.setCustomerId(((User) request.getSession(true).getAttribute(
					LoginServlet.SESSION_USER)).getId());
			orderService.saveOrder(order);
			int orderId = orderService.getOrderByName(orderName).getId();
			request.getRequestDispatcher("/orderdetails?orderid=" + orderId)
					.forward(request, response);
		} else {
			LOG.info("failed to create order. empty order name");
			request.getRequestDispatcher("/customer")
					.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("crate new order do post");
		Connection connection = ConnectionManager.getConnection();
		try {
			LOG.info("set autocominig - false");
			connection.setAutoCommit(false);
			Order order = new Order();

			String orderName = request.getParameter("ordername");
			String orderDescription = request.getParameter("orderdescription");
			String additionalInfo = request.getParameter("additionalinfo");
			User sessionUser = (User) request.getSession(true).getAttribute(
					LoginServlet.SESSION_USER);
			order.setName(orderName);
			order.setDescription(orderDescription);
			order.setAdditionalInformation(additionalInfo);
			order.setCustomerId(sessionUser.getId());
			orderService.saveOrder(order);

			order = orderService.getOrderByName(orderName);

			for (Task task : taskService.getAll()) {

				String taskId = task.getId().toString();

				String taskDeveloperQuantity = request.getParameter(taskId
						+ "developers");

				DateFormat df = TaskLoad.dateFormat;

				String startDateString = request.getParameter(taskId
						+ "startdate");
				String endDateString = request.getParameter(taskId + "enddate");

				if (taskId.isEmpty() || taskDeveloperQuantity.isEmpty()
						|| startDateString.isEmpty() || endDateString.isEmpty()) {
					continue;
				}

				Date startDate = df.parse(startDateString);
				Date endDate = df.parse(endDateString);

				LOG.info(taskId + " " + " " + taskDeveloperQuantity + " "
						+ df.format(startDate) + " " + df.format(endDate));

				TaskLoad taskLoad = new TaskLoad();
				taskLoad.setOrderId(order.getId());
				taskLoad.setTask(taskService.getTaskById(Integer
						.valueOf(taskId)));

				taskLoad.setStartDate(startDate);
				taskLoad.setEndDate(endDate);
				taskLoad.setDeveloperQuantity(Integer
						.valueOf(taskDeveloperQuantity));
				taskLoadService.saveTaskLoad(taskLoad);
			}

			LOG.info("commiting ... ");
			connection.commit();
			request.getRequestDispatcher("/customer")
					.forward(request, response);

		} catch (SQLException | ParseException e) {
			try {
				LOG.info("exception - roll back order creating - true");
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				LOG.info("set autocominig - true");
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
