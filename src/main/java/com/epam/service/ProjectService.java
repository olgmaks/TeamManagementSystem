package com.epam.service;

import java.util.List;

import com.epam.bean.Project;
import com.epam.dao.ProjectDao;

public class ProjectService {

	private ProjectDao projectDao;

	public ProjectService() {
		projectDao = new ProjectDao();
	}

	public Project getProjectById(int id) {
		return projectDao.getProjectById(id);
	}

	public int getLastProjectNumber() {
		return projectDao.getLastProjectNumber();
	}

	public List<Project> getAll() {
		return projectDao.getAll();
	}

	public void saveProject(Project project) {
		projectDao.saveProject(project);		
	}

	public void updateProject(Project project) {
		projectDao.updateProject(project);
		
	}
}
