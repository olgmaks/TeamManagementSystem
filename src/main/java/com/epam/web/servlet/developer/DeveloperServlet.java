package com.epam.web.servlet.developer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.bean.Developer;
import com.epam.bean.Order;
import com.epam.bean.Project;
import com.epam.bean.ProjectDeveloper;
import com.epam.bean.User;
import com.epam.service.DeveloperService;
import com.epam.service.OrderService;
import com.epam.service.ProjectDeveloperService;
import com.epam.service.ProjectService;
import com.epam.web.servlet.login.LoginServlet;

public class DeveloperServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(DeveloperServlet.class);

	private DeveloperService developerService;
	private ProjectDeveloperService projectDeveloperService;
	private OrderService orderService;
	private ProjectService projectService;

	public DeveloperServlet() {
		super();
		developerService = new DeveloperService();
		projectDeveloperService = new ProjectDeveloperService();
		projectService = new ProjectService();
		orderService = new OrderService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do get");
		User user = (User) request.getSession(true).getAttribute(
				LoginServlet.SESSION_USER);
		Developer developer = developerService.getDeveloperByUserId(user
				.getId());
		List<ProjectDeveloper> projectDevelopers = projectDeveloperService
				.getAllByDeveloperId(developer.getId());
		List<Project> projects = projectService.getAll();
		List<Order> orders = orderService.getAll();

		request.setAttribute("firstname", user.getFirstName());
		request.setAttribute("lastname", user.getLastName());
		request.setAttribute("specialization", developer.getTask()
				.getSpecialization());
		request.setAttribute("projectDevelopers", projectDevelopers);
		request.setAttribute("projects", projects);
		request.setAttribute("orders", orders);

		request.getRequestDispatcher("/pages/developerpage.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do post");
		doGet(request, response);
	}

}
