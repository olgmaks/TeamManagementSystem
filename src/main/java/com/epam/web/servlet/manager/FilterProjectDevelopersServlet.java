package com.epam.web.servlet.manager;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.bean.Developer;
import com.epam.bean.TaskLoad;
import com.epam.service.DeveloperService;

public class FilterProjectDevelopersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(FilterProjectDevelopersServlet.class);

	private DeveloperService developerService;

	public FilterProjectDevelopersServlet() {
		super();
		developerService = new DeveloperService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.info("do get");
		try {
			String dateRestrictionString = request
					.getParameter("daterestriction");
			String specializationRestriction = request
					.getParameter("specializationrestriction");
			Date dateRestriction = null;
			Integer projectId = (Integer) request.getSession(true)
					.getAttribute("projectid");
			if (!dateRestrictionString.isEmpty()) {
				dateRestriction = TaskLoad.dateFormat
						.parse(dateRestrictionString);
			}

			List<Developer> developers = developerService
					.getDevelopersWithRestrictions(specializationRestriction,
							dateRestriction, projectId);

			request.setAttribute("developers", developers);
			request.setAttribute("fiteredsize", developers.size());
			request.setAttribute("specializationrestriction",
					specializationRestriction);
			request.setAttribute("daterestriction", dateRestrictionString);
			request.getRequestDispatcher("/pages/projectdevelopers.jsp")
					.forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
			LOG.error(e);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
