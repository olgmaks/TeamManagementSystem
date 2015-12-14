package com.epam.service;

import java.util.List;

import com.epam.bean.TaskLoad;
import com.epam.dao.TaskLoadDao;

public class TaskLoadService {

	private TaskLoadDao taskLoadDao;

	public TaskLoadService() {
		taskLoadDao = new TaskLoadDao();
	}

	public void saveTaskLoad(TaskLoad taskLoad) {
		taskLoadDao.saveTaskLoad(taskLoad);
	}

	public void updateTaskLoad(TaskLoad taskLoad) {
		taskLoadDao.updateTaskLoad(taskLoad);
	}

	public void deleteTaskLoad(int id) {
		taskLoadDao.deleteTaskLoad(id);
	}

	public List<TaskLoad> getAllLoadTasksByOrderId(int id) {
		return taskLoadDao.getAllLoadTasksByOrderId(id);
	}
}
