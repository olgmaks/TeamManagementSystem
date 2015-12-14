package com.epam.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.bean.Task;
import com.epam.lab.dbutils.ConnectionManager;

public class TaskDao {

	private static final String GET_ALL_TASKS = "select * from task";
	private static final String GET_TASK_BY_ID = "select * from task t where t.id = ? ";

	public Task getTaskById(int id) {
		Task result = new Task();
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(GET_TASK_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			result = toTask(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Task> getAll() {
		List<Task> result = new ArrayList<Task>();
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(GET_ALL_TASKS);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(toTask(resultSet));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private Task toTask(ResultSet resultSet) {
		Task result = new Task();
		try {
			result.setId(resultSet.getInt("id"));
			result.setSpecialization(resultSet.getString("specialization"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
