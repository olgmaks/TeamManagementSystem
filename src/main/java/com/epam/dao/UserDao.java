package com.epam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.bean.User;
import com.epam.lab.dbutils.ConnectionManager;

public class UserDao {

	private static final String GET_ALL_USERS = "SELECT * FROM user u";
	private static final String GET_USER_BY_ID = "SELECT * FROM USER u WHERE u.id = ?";
	private static final String GET_USER_BY_EMAIL = "SELECT * FROM USER u WHERE u.`e-mail` = ?";
	private static final String INSERT_USER = "INSERT INTO user "
			+ "(first_name, last_name, sex, `e-mail`, password, role) VALUES (?,?,?,?,?,?)";
	private static final String UPDATE_USER = "UPDATE user u SET u.role=? WHERE u.id=?";

	private Connection connection;

	public UserDao() {
		connection = ConnectionManager.getConnection();
	}

	public User getUserById(int id) {
		User user = new User();
		try {
			PreparedStatement statement = connection
					.prepareStatement(GET_USER_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			user = toUser(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User getUserByEmail(String email) {
		User user = new User();
		try {
			PreparedStatement statement = connection
					.prepareStatement(GET_USER_BY_EMAIL);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			user = toUser(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<User> getAll() {
		List<User> result = new ArrayList<User>();
		try {
			ResultSet resultSet = connection.prepareStatement(GET_ALL_USERS)
					.executeQuery();
			while (resultSet.next()) {
				result.add(toUser(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void updateUser(User user){
		try {
			PreparedStatement statement = connection
					.prepareStatement(UPDATE_USER);
			statement.setString(1, user.getRole());
			statement.setInt(2, user.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveUser(User user) {
		try {
			PreparedStatement statement = connection
					.prepareStatement(INSERT_USER);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getSex());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPassword());
			statement.setString(6, user.getRole());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private User toUser(ResultSet resultSet) {
		User user = new User();
		try {
			user.setId(resultSet.getInt("id"));
			user.setFirstName(resultSet.getString("first_name"));
			user.setLastName(resultSet.getString("last_name"));
			user.setSex(resultSet.getString("sex"));
			user.setPassword(resultSet.getString("password"));
			user.setEmail(resultSet.getString("e-mail"));
			user.setRole(resultSet.getString("role"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
