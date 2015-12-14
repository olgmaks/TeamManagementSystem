package com.epam.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.bean.Developer;
import com.epam.bean.TaskLoad;

public class DeveloperDaoTest {

	private static DeveloperDao developerDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		developerDao = new DeveloperDao();
	}

	// @Test
	public void testGet() {
		System.out.println(developerDao.getDeveloperById(1));
	}

	// @Test
	public void testGetAll() {
		List<Developer> result = developerDao.getAll();
		for (Developer developer : result) {
			System.out.println(developer);
		}
	}

//	@Test
	public void getWithSpecRestriction() {
		List<Developer> result = developerDao
				.getDevelopersWithSpecializationRestriction("architectural");
		System.out.println("size : " + result.size());
		for (Developer developer : result) {
			System.out.println(developer);
		}
	}
	@Test
	public void getWithDateRestriction() {
		Date date = null;
		try {
			date = TaskLoad.dateFormat.parse("");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(date);
		List<Developer> result = developerDao
				.getDevelopersWithDateRestriction(date);
		System.out.println("size : " + result.size());
		for (Developer developer : result) {
			System.out.println(developer);
		}
	}
}
