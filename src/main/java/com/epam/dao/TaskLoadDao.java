package com.epam.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.epam.bean.TaskLoad;
import com.epam.lab.dbutils.ConnectionManager;

public class TaskLoadDao {

	private static final String GET_ALL_TASK_LOADS_BY_ORDER_ID = "select * "
			+ "from task_load tl  where tl.order_id= ?";

	private static final String SAVE_TASK_LOAD = "INSERT INTO task_load(order_id, task_id, developer_quantity, load_hours, start_date, end_date)  VALUES (?,?,?,?,?,?)";
	private static final String UPDATE_TASK_LOAD = "UPDATE task_load tl "
			+ "SET tl. order_id= ? ,tl.task_id=?, tl.developer_quantity=?, tl.load_hours=? "
			+ "WHERE tl.id=?";
	private static final String DELETE_TASK_LOAD = "DELETE FROM task_load WHERE task_load.id = ?";

	private TaskDao taskDao;

	public TaskLoadDao() {
		taskDao = new TaskDao();
	}

	public void saveTaskLoad(TaskLoad taskLoad) {
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(SAVE_TASK_LOAD);
			statement.setInt(1, taskLoad.getOrderId());
			statement.setInt(2, taskLoad.getTask().getId());
			statement.setInt(3, taskLoad.getDeveloperQuantity());

			long startDate = taskLoad.getStartDate().getTime();
			long endDate = taskLoad.getEndDate().getTime();
			long difference = endDate - startDate;
			Integer loadHours = (int) (TimeUnit.MILLISECONDS
					.toHours(difference));

			statement.setInt(4, loadHours);

			DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
			statement.setString(5, dateFormat.format(taskLoad.getStartDate()));
			statement.setString(6, dateFormat.format(taskLoad.getEndDate()));

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateTaskLoad(TaskLoad taskLoad) {
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(UPDATE_TASK_LOAD);
			statement.setInt(1, taskLoad.getOrderId());
			statement.setInt(2, taskLoad.getTask().getId());
			statement.setInt(3, taskLoad.getDeveloperQuantity());
			statement.setInt(4, taskLoad.getLoadHours());
			statement.setInt(5, taskLoad.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteTaskLoad(int id) {
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(DELETE_TASK_LOAD);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<TaskLoad> getAllLoadTasksByOrderId(int id) {
		List<TaskLoad> result = new ArrayList<TaskLoad>();
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(GET_ALL_TASK_LOADS_BY_ORDER_ID);
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(toTaskLoad(resultSet));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public TaskLoad toTaskLoad(ResultSet resultSet) {
		TaskLoad result = new TaskLoad();
		try {
			// result.setLoadHours(resultSet.getInt("load_hours"));
			result.setId(resultSet.getInt("id"));
			result.setStartDate(resultSet.getDate("start_date"));
			result.setEndDate(resultSet.getDate("end_date"));
			result.setOrderId(resultSet.getInt("order_id"));
			result.setDeveloperQuantity(resultSet.getInt("developer_quantity"));
			result.setLoadHours(resultSet.getInt("load_hours"));
			result.setTask(taskDao.getTaskById(resultSet.getInt("task_id")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
