package com.epam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.epam.bean.Developer;
import com.epam.lab.dbutils.ConnectionManager;

public class DeveloperDao {

	private static final String GET_ALL_DEVELOPERS = "SELECT * FROM developer d";
	private static final String GET_DEVELOPERS_WITH_SPECIALIZATION_RESTRICTION = "SELECT  DISTINCT "
			+ "d.id,d.user_id,d.free_hours,d.specialization,d.rate  "
			+ "FROM developer d JOIN task t ON d.specialization = t.id"
			+ " WHERE t.specialization REGEXP ?";
	private static final String GET_DEVELOPERS_WITH_DATE_RESTRICTION = "SELECT  "
			+ "DISTINCT d.id,d.user_id,d.free_hours,d.specialization,d.rate "
			+ "FROM developer d JOIN project_developper pd ON d.id = pd.developer_id "
			+ "WHERE pd.end_date < ?";
	private static final String SAVE_DEVELOPER = "INSERT INTO  developer (user_id, free_hours, specialization, rate) VALUES (?,0,?,?)";
	private static final String GET_DEVELOPER_BY_ID = "SELECT * FROM developer d  WHERE d.id = ? ";
	private static final String GET_DEVELOPER_BY_USER_ID = "SELECT * FROM developer d  WHERE d.user_id = ? ";
	private static final String GET_DEVELOPERS_BY_PROJECT_ID = "SELECT d.id, d.user_id, d.free_hours, d.specialization, d.rate "
			+ "FROM developer d JOIN project_developper pd ON d.id = pd.developer_id "
			+ "WHERE pd.project_id = ?";
	// private static final String SAVE_DEVELOPER = "";
	// private static final String UPDATE_DEVELOPER = "";

	private Connection connection;
	private ProjectDeveloperDao projectDeveloperDao;
	private UserDao userDao;
	private TaskDao taskDao;

	public DeveloperDao() {
		connection = ConnectionManager.getConnection();
		projectDeveloperDao = new ProjectDeveloperDao();
		userDao = new UserDao();
		taskDao = new TaskDao();
	}

	public void saveDeveloper(Developer developer) {
		try {
			PreparedStatement statement = connection
					.prepareStatement(SAVE_DEVELOPER);
			statement.setInt(1, developer.getUserId());
			statement.setInt(2, developer.getTask().getId());
			statement.setDouble(3, developer.getRate());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Developer getDeveloperById(int id) {
		Developer result = new Developer();
		try {
			PreparedStatement statement = connection
					.prepareStatement(GET_DEVELOPER_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			result = toDeveloper(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Developer> getDevelopersByProjectId(int projectId) {
		List<Developer> result = new ArrayList<Developer>();
		try {
			PreparedStatement statement = connection
					.prepareStatement(GET_DEVELOPERS_BY_PROJECT_ID);
			statement.setInt(1, projectId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(toDeveloper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Developer> getAll() {
		List<Developer> result = new ArrayList<Developer>();
		try {
			PreparedStatement statement = connection
					.prepareStatement(GET_ALL_DEVELOPERS);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(toDeveloper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Developer getDeveloperByUserId(Integer id) {
		Developer result = new Developer();
		try {
			PreparedStatement statement = connection
					.prepareStatement(GET_DEVELOPER_BY_USER_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			result = toDeveloper(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Developer> getDevelopersWithSpecializationRestriction(
			String specializationRestriction) {
		List<Developer> result = new ArrayList<Developer>();
		try {
			PreparedStatement statement = connection
					.prepareStatement(GET_DEVELOPERS_WITH_SPECIALIZATION_RESTRICTION);

			if (specializationRestriction == null
					|| specializationRestriction.isEmpty()) {
				specializationRestriction = "...";
			}
			statement.setString(1, specializationRestriction);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(toDeveloper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Developer> getDevelopersWithDateRestriction(Date date) {
		List<Developer> result = new ArrayList<Developer>();

		try {
			PreparedStatement statement = connection
					.prepareStatement(GET_DEVELOPERS_WITH_DATE_RESTRICTION);

			if (date != null) {
				statement.setDate(1, new java.sql.Date(date.getTime()));
			} else {
				statement.setString(1, "...");
			}
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(toDeveloper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private Developer toDeveloper(ResultSet resultSet) {
		Developer result = null;
		try {
			int user_id = resultSet.getInt("user_id");
			int developer_id = resultSet.getInt("id");
			result = new Developer(userDao.getUserById(user_id));
			result.setId(developer_id);
			result.setRole("developer");
			result.setRate(resultSet.getDouble("rate"));
			result.setFreeHours(resultSet.getInt("free_hours"));
			result.setTask(taskDao.getTaskById(resultSet
					.getInt("specialization")));
			result.setProjects(projectDeveloperDao
					.getAllByDeveloperId(developer_id));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
