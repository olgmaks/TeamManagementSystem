package com.epam.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.bean.ProjectDeveloper;
import com.epam.lab.dbutils.ConnectionManager;

public class ProjectDeveloperDao {

	private static final String GET_ALL_BY_DEVELOPER_ID = "SELECT * FROM project_developper pd WHERE pd.developer_id = ?";
	private static final String GET_ALL_BY_PROJECT_ID = "SELECT * FROM  project_developper pd WHERE pd.project_id = ?";
	private static final String GET_BY_ID = "SELECT * FROM project_developper pd WHERE pd.project_id=? AND pd.developer_id = ?";
	private static final String SAVE_PROJECT_DEVELOPER = "INSERT INTO project_developper (project_id, developer_id, start_date, end_date, price) VALUES (?,?,?,?,?)";
	private static final String REMOVE_PROJECT_DEVELOPER = "DELETE FROM project_developper WHERE project_id=? AND developer_id=?";
	private static final String UPDATE_PROJECT_DEVELOPER = "UPDATE project_developper pd SET pd.hours=?, pd.start_date=?, pd.end_date=?, pd.price=? WHERE pd.project_id = ? AND pd.developer_id=?";

	public void saveProjectDeveloper(ProjectDeveloper projectDeveloper) {
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(SAVE_PROJECT_DEVELOPER);
			statement.setInt(1, projectDeveloper.getProjectId());
			statement.setInt(2, projectDeveloper.getDeveloperId());

			statement.setDate(3, new Date(projectDeveloper.getStartDate()
					.getTime()));
			statement.setDate(4, new Date(projectDeveloper.getEndDate()
					.getTime()));

			statement.setDouble(5, projectDeveloper.getPrice());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateProjectDeveloper(ProjectDeveloper projectDeveloper) {
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(UPDATE_PROJECT_DEVELOPER);

			statement.setInt(1, projectDeveloper.getHours());
			statement.setDate(2, new Date(projectDeveloper.getStartDate()
					.getTime()));
			statement.setDate(3, new Date(projectDeveloper.getEndDate()
					.getTime()));
			statement.setDouble(4, projectDeveloper.getPrice());
			statement.setInt(5, projectDeveloper.getProjectId());
			statement.setInt(6, projectDeveloper.getDeveloperId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ProjectDeveloper getById(int projectId, int developerId) {
		ProjectDeveloper result = new ProjectDeveloper();
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(GET_BY_ID);
			statement.setInt(1, projectId);
			statement.setInt(2, developerId);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			result = toProjectDeveloper(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<ProjectDeveloper> getAllByDeveloperId(int id) {
		List<ProjectDeveloper> result = new ArrayList<ProjectDeveloper>();
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(GET_ALL_BY_DEVELOPER_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				result.add(toProjectDeveloper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<ProjectDeveloper> getAllByProjectId(int id) {
		List<ProjectDeveloper> result = new ArrayList<ProjectDeveloper>();
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(GET_ALL_BY_PROJECT_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(toProjectDeveloper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void removeProjectDeveloper(int projectId, int developerId) {
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(REMOVE_PROJECT_DEVELOPER);
			statement.setInt(1, projectId);
			statement.setInt(2, developerId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private ProjectDeveloper toProjectDeveloper(ResultSet resultSet) {
		ProjectDeveloper result = new ProjectDeveloper();
		try {
			result.setDeveloperId(resultSet.getInt("developer_id"));
			result.setProjectId(resultSet.getInt("project_id"));
			result.setHours(resultSet.getInt("hours"));
			result.setStartDate(resultSet.getDate("start_date"));
			result.setEndDate(resultSet.getDate("end_date"));
			result.setPrice(resultSet.getDouble("price"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
