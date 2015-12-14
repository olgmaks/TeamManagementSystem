package com.epam.service;

import java.text.ParseException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.bean.Developer;
import com.epam.bean.TaskLoad;

public class DeveloperServiceTest {

	private static DeveloperService developerService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		developerService = new DeveloperService();
	}

	// @Test
	public void testGet() {
		System.out.println(developerService.getDeveloperById(1));
	}

	// @Test
	public void testGetAll() {
		List<Developer> result = developerService.getAll();
		for (Developer developer : result) {
			System.out.println(developer);
		}
	}

	@Test
	public void getWithSpecRestriction() throws ParseException {
		List<Developer> result = developerService
				.getDevelopersWithRestrictions("architectural",
						TaskLoad.dateFormat.parse("30.01.2015"),74859);
		System.out.println("size : " + result.size());

		for (Developer developer : result) {
			System.out.println(developer);
		}
	}
}
