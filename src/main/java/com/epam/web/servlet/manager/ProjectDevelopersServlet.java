package com.epam.web.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.bean.Developer;
import com.epam.service.DeveloperService;

public class ProjectDevelopersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(ProjectDevelopersServlet.class);

	private DeveloperService developerService;

	public ProjectDevelopersServlet() {
		super();
		developerService = new DeveloperService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do get");
		int projectId = (int) request.getSession(true)
				.getAttribute("projectid");
		List<Developer> developersOnProject = developerService
				.getDevelopersByProjectId(projectId);
		List<Developer> developers = developerService.getAll();
		developers.removeAll(developersOnProject);

		request.setAttribute("developers", developers);
		request.setAttribute("fiteredsize", developers.size());
		request.getRequestDispatcher("/pages/projectdevelopers.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do post");
	}

}
