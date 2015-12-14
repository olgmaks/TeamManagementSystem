package com.epam.web.servlet.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.bean.Project;
import com.epam.service.ProjectService;

public class RegistrateProjectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(RegistrateProjectServlet.class);

	private ProjectService projectService;

	public RegistrateProjectServlet() {
		super();
			projectService = new ProjectService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do get");
		int orderId = Integer.valueOf(request.getParameter("orderid"));

		Project project = new Project(
				(projectService.getLastProjectNumber() + 1), orderId,
				"in progress", 0.0);
		project.setStatus("in progress");
		projectService.saveProject(project);
		request.getRequestDispatcher("/projectdetails?id=" + project.getId())
				.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
