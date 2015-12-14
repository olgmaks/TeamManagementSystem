package com.epam.dao;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.bean.Project;

public class ProjectDaoTest {
	
	private static ProjectDao projectDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		projectDao = new ProjectDao();
	}

//	@Test
	public void testGetProjectById() {
		System.out.println(projectDao.getProjectById(73265));
	}
	
//	@Test
	public void testGetAllProjects() {
		for (Project p : projectDao.getAll()) {
			System.out.println(p);
		}
	}
	
	@Test
	public void testGetById () {
		Project p = projectDao.getProjectById(72395);
		System.out.println(p);
	}
	

}
