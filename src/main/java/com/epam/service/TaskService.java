package com.epam.service;

import java.util.List;

import com.epam.bean.Task;
import com.epam.dao.TaskDao;

public class TaskService {
	
	private TaskDao taskDao;
	
	public TaskService() {
		taskDao = new TaskDao();
	}
	
	public Task getTaskById(int id) {
		return taskDao.getTaskById(id);
	}

	public List<Task> getAll() {
		return taskDao.getAll();
	}
}
