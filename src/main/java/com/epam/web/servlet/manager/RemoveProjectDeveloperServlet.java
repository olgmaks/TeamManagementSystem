package com.epam.web.servlet.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.service.ProjectDeveloperService;

public class RemoveProjectDeveloperServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(RemoveProjectDeveloperServlet.class);
	private ProjectDeveloperService projectDeveloperService;

	public RemoveProjectDeveloperServlet() {
		super();
		projectDeveloperService = new ProjectDeveloperService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do get");
		int projectId = Integer.valueOf(request.getParameter("projectid"));
		int developerId = Integer.valueOf(request.getParameter("developerid"));

		projectDeveloperService.removeProjectDeveloper(projectId, developerId);

		request.getRequestDispatcher(
				String.format("/projectdetails?id=%s", projectId)).forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
