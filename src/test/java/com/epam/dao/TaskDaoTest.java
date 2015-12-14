package com.epam.dao;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.bean.Task;

public class TaskDaoTest {

	private static TaskDao taskDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		taskDao = new TaskDao();
	}

	@Test
	public void testGetTaskById() {
		System.out.println(taskDao.getTaskById(11));
	}

	@Test
	public void testGetAll() {
		
		List<Task> list = taskDao.getAll();
		for (Task task : list) {
			System.out.println(task);
		}
	}
}
