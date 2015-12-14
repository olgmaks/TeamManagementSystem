package com.epam.web.servlet.manager;

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
import com.epam.bean.TaskLoad;
import com.epam.service.DeveloperService;
import com.epam.service.OrderService;
import com.epam.service.ProjectDeveloperService;
import com.epam.service.ProjectService;
import com.epam.service.TaskLoadService;

public class ProjectDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(ProjectDetailServlet.class);
	private ProjectService projectService;
	private OrderService orderService;
	private TaskLoadService taskLoadService;
	private DeveloperService developerService;
	private ProjectDeveloperService projectDeveloperService;

	public ProjectDetailServlet() {
		super();
		projectService = new ProjectService();
		orderService = new OrderService();
		taskLoadService = new TaskLoadService();
		developerService = new DeveloperService();
		projectDeveloperService = new ProjectDeveloperService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("project detail do get");
		int id = Integer.valueOf(request.getParameter("id"));
		request.getSession(true).setAttribute("projectid", id);

		Project project = projectService.getProjectById(id);
		Order order = orderService.getOrderById(project.getOrderId());
		List<TaskLoad> tasks = taskLoadService.getAllLoadTasksByOrderId(order
				.getId());
		List<Developer> developers = developerService.getAll();
		List<ProjectDeveloper> projectDevelopers = projectDeveloperService
				.getAllByProjectId(id);

		request.setAttribute("projectname", order.getName());
		request.setAttribute("description", order.getDescription());
		request.setAttribute("additionalinfo", order.getAdditionalInformation());
		request.setAttribute("projectprice", project.getPrice());
		request.setAttribute("status", project.getStatus());

		request.setAttribute("tasks", tasks);
		request.setAttribute("developers", developers);
		request.setAttribute("projectdevelopers", projectDevelopers);

		request.getRequestDispatcher("pages/projectdetails.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("project detail do post");
	}

}
