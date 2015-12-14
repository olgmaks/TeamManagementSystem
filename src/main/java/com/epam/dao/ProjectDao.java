package com.epam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.bean.Project;
import com.epam.lab.dbutils.ConnectionManager;

public class ProjectDao {

	private static final String GET_PROJECT_BY_ID = "select * from project p where p.id = ?";
	private static final String GET_ALL_PROJECTS = "select * from project ";
	private static final String GET_MAX_PROJECT_ID = "SELECT MAX(p.id) AS 'maximal_value' FROM project p";
	private static final String SAVE_PROJECT = "INSERT INTO project VALUES (?,?,?,?)";
	private static final String UPDATE_PROJECT = "UPDATE project p SET p.status = ?, p.price = ? where p.id = ?";
	// private static final String DELETE_PROJECT = "";

	private Connection connection;
	private ProjectDeveloperDao projectDeveloperDao;

	public ProjectDao() {
		connection = ConnectionManager.getConnection();
		projectDeveloperDao = new ProjectDeveloperDao();
	}

	public void saveProject(Project project) {
		try {
			PreparedStatement statement = connection
					.prepareStatement(SAVE_PROJECT);
			statement.setInt(1, project.getId());
			statement.setInt(2, project.getOrderId());
			statement.setString(3, project.getStatus());
			statement.setDouble(4, project.getPrice());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getLastProjectNumber() {
		Integer result = 0;
		try {
			PreparedStatement statement = connection
					.prepareStatement(GET_MAX_PROJECT_ID);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			result = resultSet.getInt("maximal_value");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Project getProjectById(int id) {
		Project result = new Project();
		try {
			PreparedStatement statement = connection
					.prepareStatement(GET_PROJECT_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			result = toProject(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Project> getAll() {
		List<Project> result = new ArrayList<Project>();
		try {
			PreparedStatement statement = connection
					.prepareStatement(GET_ALL_PROJECTS);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(toProject(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void updateProject(Project project) {
		try {
			PreparedStatement statement = connection
					.prepareStatement(UPDATE_PROJECT);
			statement.setString(1, project.getStatus());
			statement.setDouble(2, project.getPrice());
			statement.setInt(3, project.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Project toProject(ResultSet resultSet) {
		Project result = new Project();
		try {
			result.setId(resultSet.getInt("id"));
			result.setOrderId(resultSet.getInt("order_id"));
			result.setPrice(resultSet.getDouble("price"));
			result.setStatus(resultSet.getString("status"));
			result.setDevelopers(projectDeveloperDao.getAllByProjectId(result
					.getId()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
