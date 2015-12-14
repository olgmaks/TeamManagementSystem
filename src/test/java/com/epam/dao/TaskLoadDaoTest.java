package com.epam.dao;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.bean.TaskLoad;

public class TaskLoadDaoTest {

	private static TaskDao taskDao;
	private static TaskLoadDao taskLoadDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		taskLoadDao = new TaskLoadDao();
		taskDao = new TaskDao();
	}

//	@Test
	public void testGet() {
		System.out.println("get all task dao test");
		List<TaskLoad> list = taskLoadDao.getAllLoadTasksByOrderId(1);
		for (TaskLoad taskLoad : list) {
			System.out.println(taskLoad);
		}
	}

//	 @Test
	public void testSave() {
		TaskLoad load = new TaskLoad();
		load.setDeveloperQuantity(2);
		load.setOrderId(1);
		load.setStartDate("30.01.2015");
		load.setEndDate("20.05.2015");
		load.setTask(taskDao.getTaskById(56));
		taskLoadDao.saveTaskLoad(load);
	}

	// @Test
	public void testUpdate() {
		TaskLoad load = new TaskLoad();
		load.setLoadHours(250);
		load.setOrderId(1);
		load.setTask(taskDao.getTaskById(56));
		taskLoadDao.updateTaskLoad(load);
	}

	 @Test
	public void testDelete() {
		TaskLoad load = new TaskLoad();
		load.setLoadHours(250);
		load.setOrderId(1);
		load.setTask(taskDao.getTaskById(11));
		taskLoadDao.deleteTaskLoad(27);
	}
}
