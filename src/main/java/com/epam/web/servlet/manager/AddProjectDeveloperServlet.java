package com.epam.web.servlet.manager;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.bean.Developer;
import com.epam.bean.Project;
import com.epam.bean.TaskLoad;
import com.epam.service.DeveloperService;
import com.epam.service.ProjectDeveloperService;
import com.epam.service.ProjectService;

public class AddProjectDeveloperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AddProjectDeveloperServlet.class);
	
	private DeveloperService developerService;
	private ProjectService projectService;
	private ProjectDeveloperService projectDeveloperService;

	public AddProjectDeveloperServlet() {
		super();
		developerService = new DeveloperService();
		projectService = new ProjectService();
		projectDeveloperService = new ProjectDeveloperService();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do get");
		Integer developerId = Integer.valueOf(request
				.getParameter("developerid"));
		Integer projectId = (Integer) request.getSession(true).getAttribute(
				"projectid");
		String startDate = request.getParameter("startdate" + developerId);
		String endDate = request.getParameter("enddate" + developerId);

		DateFormat dateFormat = TaskLoad.dateFormat;
		if (!startDate.isEmpty() && !endDate.isEmpty()) {
			try {
				Project project = projectService.getProjectById(projectId);
				Developer developer = developerService
						.getDeveloperById(developerId);
				projectDeveloperService.saveProjectDeveloper(project,
						developer, dateFormat.parse(startDate),
						dateFormat.parse(endDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(
				String.format("/projectdetails?id=%s", projectId)).forward(
				request, response);
		;

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
