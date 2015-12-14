package com.epam.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.epam.bean.Developer;
import com.epam.bean.User;
import com.epam.dao.DeveloperDao;

public class DeveloperService {

	private DeveloperDao developerDao;
	private UserService userService;

	public DeveloperService() {
		developerDao = new DeveloperDao();
		userService = new UserService();
	}

	public Developer getDeveloperById(int id) {
		return developerDao.getDeveloperById(id);
	}

	public List<Developer> getAll() {
		return developerDao.getAll();
	}

	public Developer getDeveloperByUserId(Integer id) {
		return developerDao.getDeveloperByUserId(id);
	}
	
	public List<Developer> getDevelopersByProjectId(int id){
		return developerDao.getDevelopersByProjectId(id);
	}
	

	public List<Developer> getDevelopersWithRestrictions(
			String specializationRestriction, Date dateRestriction,
			int projectId) {
		List<Developer> result = new ArrayList<Developer>();
		List<Developer> fiteredBySpecialization = developerDao
				.getDevelopersWithSpecializationRestriction(specializationRestriction);
		List<Developer> filteredByDate = developerDao
				.getDevelopersWithDateRestriction(dateRestriction);
		List<Developer> filteredByProject = developerDao
				.getDevelopersByProjectId(projectId);
		
		result.addAll(fiteredBySpecialization);
		result.removeAll(filteredByDate);
		result.removeAll(filteredByProject);
		return result;
	}

	public void saveDeveloper(Developer developer) {
		developerDao.saveDeveloper(developer);
		User user = new User();
		user.setId(developer.getUserId());
		user.setRole("developer");
		userService.updateUser(user);
	}
}
