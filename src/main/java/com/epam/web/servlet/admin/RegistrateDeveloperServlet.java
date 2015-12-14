package com.epam.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.bean.Developer;
import com.epam.bean.User;
import com.epam.service.DeveloperService;
import com.epam.service.TaskService;
import com.epam.service.UserService;

public class RegistrateDeveloperServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(RegistrateDeveloperServlet.class);

	private UserService userService;
	private TaskService taskService;
	private DeveloperService developerService;

	public RegistrateDeveloperServlet() {
		super();
		userService = new UserService();
		taskService = new TaskService();
		developerService = new DeveloperService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do get");

		String userIdString = request.getParameter("userid");
		String rateString = request.getParameter("rate");
		String specializationIdString = request.getParameter("specialization");

		if (!userIdString.isEmpty() && !rateString.isEmpty()
				&& !specializationIdString.isEmpty()) {
			Integer userId = Integer.valueOf(userIdString);
			Double rate = Double.valueOf(rateString);
			Integer specializationId = Integer.valueOf(specializationIdString);

			User user = userService.getUserById(userId);

			Developer developer = new Developer(user);
			developer.setTask(taskService.getTaskById(specializationId));
			developer.setRate(rate);

			developerService.saveDeveloper(developer);
			LOG.info("developer has been saved " + developer);
		} else {
			LOG.info("developer was not saved ");
		}
		request.getRequestDispatcher("/admin").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
