package com.epam.dao;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.bean.ProjectDeveloper;

public class ProjectDeveloperDaoTest {

	private static ProjectDeveloperDao projectDeveloperDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		projectDeveloperDao = new ProjectDeveloperDao();
	}

	@Test
	public void testGet() {
		List<ProjectDeveloper> devproj = projectDeveloperDao
				.getAllByDeveloperId(1);
		for (ProjectDeveloper projectDeveloper : devproj) {
			System.out.println(projectDeveloper);
		}
	}

}
