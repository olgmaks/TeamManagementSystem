package com.epam.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.epam.bean.Developer;
import com.epam.bean.Project;
import com.epam.bean.ProjectDeveloper;
import com.epam.dao.ProjectDeveloperDao;

public class ProjectDeveloperService {

	private ProjectDeveloperDao projectDeveloperDao;
	private ProjectService projectService;

	public ProjectDeveloperService() {
		projectDeveloperDao = new ProjectDeveloperDao();
		projectService = new ProjectService();
	}

	public List<ProjectDeveloper> getAllByProjectId(int id) {
		return projectDeveloperDao.getAllByProjectId(id);
	}

	public List<ProjectDeveloper> getAllByDeveloperId(int id) {
		return projectDeveloperDao.getAllByDeveloperId(id);
	}

	public void saveProjectDeveloper(ProjectDeveloper projectDeveloper) {
		projectDeveloperDao.saveProjectDeveloper(projectDeveloper);
	}

	public void updateProjectDeveloperHours(ProjectDeveloper projectDeveloper,
			Integer hours) {
		projectDeveloper.setHours(projectDeveloper.getHours() + hours);
		projectDeveloperDao.updateProjectDeveloper(projectDeveloper);
	}

	public void saveProjectDeveloper(Project project, Developer developer,
			Date startDate, Date endDate) {

		if (!endDate.after(startDate)) {
			return;
		}

		ProjectDeveloper projectDeveloper = new ProjectDeveloper();
		projectDeveloper.setDeveloperId(developer.getId());
		projectDeveloper.setProjectId(project.getId());
		projectDeveloper.setStartDate(startDate);
		projectDeveloper.setEndDate(endDate);

		long difference = endDate.getTime() - startDate.getTime();
		Integer loadHours = (int) (TimeUnit.MILLISECONDS.toHours(difference));
		Double developerPrice = Double.valueOf(loadHours
				* new DeveloperService().getDeveloperById(
						projectDeveloper.getDeveloperId()).getRate());

		projectDeveloper.setHours(loadHours);
		projectDeveloper.setPrice(developerPrice);
		projectDeveloperDao.saveProjectDeveloper(projectDeveloper);

		/*
		 * Updating project price
		 */
		project.setPrice(project.getPrice() + projectDeveloper.getPrice());
		projectService.updateProject(project);

	}

	public void removeProjectDeveloper(int projectId, int developerId) {
		ProjectDeveloper projectDeveloper = projectDeveloperDao.getById(
				projectId, developerId);
		Double price = projectDeveloper.getPrice();
		projectDeveloperDao.removeProjectDeveloper(projectId, developerId);
		Project project = projectService.getProjectById(projectId);
		project.setPrice(project.getPrice()
				- price
				+ (projectDeveloper.getHours() * (new DeveloperService()
						.getDeveloperById(developerId)).getRate()));
		projectService.updateProject(project);
	}
}
