package com.epam.web.servlet.developer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.bean.Developer;
import com.epam.bean.ProjectDeveloper;
import com.epam.bean.User;
import com.epam.lab.dbutils.ConnectionManager;
import com.epam.service.DeveloperService;
import com.epam.service.ProjectDeveloperService;
import com.epam.web.servlet.login.LoginServlet;

public class SubmitHoursReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(SubmitHoursReportServlet.class);
	
	private DeveloperService developerService;
	private ProjectDeveloperService projectDeveloperService;
	private Connection connection;

	public SubmitHoursReportServlet() {
		super();
		developerService = new DeveloperService();
		projectDeveloperService = new ProjectDeveloperService();
		connection = ConnectionManager.getConnection();
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

		try {
			connection.setAutoCommit(false);
			for (ProjectDeveloper projectDeveloper : projectDevelopers) {
				Integer projectId = projectDeveloper.getProjectId();
				String projectHours = request.getParameter("reportedhours"
						+ projectId);
				if (projectHours.isEmpty()) {
					continue;
				}
				projectDeveloperService.updateProjectDeveloperHours(
						projectDeveloper, Integer.valueOf(projectHours));
			}
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		request.getRequestDispatcher("/developer").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
