package com.epam.web.servlet.customer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.bean.TaskLoad;
import com.epam.service.TaskLoadService;
import com.epam.service.TaskService;

public class AddOrderTaskServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AddOrderTaskServlet.class);
	private TaskService taskService;
	private TaskLoadService taskLoadService;

	public AddOrderTaskServlet() {
		super();
		taskService = new TaskService();
		taskLoadService = new TaskLoadService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do get");
		int orderId = (int) request.getSession(true).getAttribute("orderid");

		Map<String, String[]> map = request.getParameterMap();

		Set<String> keys = map.keySet();
		Iterator<String> iterator = keys.iterator();

		String[] taskId = request.getParameterValues(iterator.next());
		String[] developers = request.getParameterValues(iterator.next());
		String[] startDate = request.getParameterValues(iterator.next());
		String[] endDate = request.getParameterValues(iterator.next());

		LOG.info(taskId + " " + developers + " " + startDate + " "
				+ endDate);

		for (int i = 0; i < keys.size(); i++) {
			if (developers[i].isEmpty() || startDate[i].isEmpty()
					|| endDate[i].isEmpty()) {
				continue;
			}

			TaskLoad taskLoad = new TaskLoad();
			taskLoad.setOrderId(orderId);
			taskLoad.setTask(taskService.getTaskById(Integer.valueOf(taskId[i])));
			taskLoad.setDeveloperQuantity(Integer.valueOf(developers[i]));
			taskLoad.setStartDate(startDate[i]);
			taskLoad.setEndDate(endDate[i]);
			taskLoadService.saveTaskLoad(taskLoad);
		}
		request.getRequestDispatcher("/orderdetails?orderid=" + orderId)
				.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
