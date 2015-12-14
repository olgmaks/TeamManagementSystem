package com.epam.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.bean.Order;
import com.epam.lab.dbutils.ConnectionManager;

public class OrderDao {

	private static final String GET_ORDER_BY_ID = "SELECT * FROM `order` o WHERE o.id = ?";
	private static final String GET_ORDER_BY_NAME = "SELECT * FROM `order` o WHERE o.name = ?";
	private static final String GET_ALL_ORDERS = "SELECT * FROM `order` ";
	private static final String GET_ALL_ORDERS_BY_CUSTOMER_ID = "SELECT * FROM `order` o WHERE o.customer_id = ?";
	private static final String GET_UNCONSIDERED_ORDERS_BY_CUSTOMER_ID = "SELECT o.id, o.name, o.description, "
			+ " o.additional_info, o.customer_id FROM `order` o "
			+ "LEFT OUTER JOIN project p  ON o.id = p.order_id  "
			+ "JOIN user u ON o.customer_id = u.id WHERE ISNULL(p.id) AND u.id=?";
	private static final String GET_UNCONSIDERED_ORDERS = "SELECT o.id, o.name, o.description, "
			+ "o.additional_info, o.customer_id FROM `order` o LEFT OUTER JOIN project p "
			+ "ON o.id = p.order_id WHERE ISNULL(p.id)";
	private static final String SAVE_ORDER = "INSERT INTO `order` "
			+ "(name, description, additional_info, customer_id) VALUES (?,?,?,?)";
	private static final String UPDATE_ORDER = "UPDATE `order` o "
			+ "SET o.id = ?, o.name = ?, o.description = ?, "
			+ "o.additional_info = ?, o.customer_id = ? WHERE o.id = ?";
	private static final String DELETE_ORDER = "DELETE FROM `order` WHERE id = ?";

	private TaskLoadDao taskLoadDao;

	public OrderDao() {
		taskLoadDao = new TaskLoadDao();
	}

	public void saveOrder(Order order) {
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(SAVE_ORDER);
			// statement.setInt(1, order.getId());
			statement.setString(1, order.getName());
			statement.setString(2, order.getDescription());
			statement.setString(3, order.getAdditionalInformation());
			statement.setInt(4, order.getCustomerId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateOrder(Order order) {
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(UPDATE_ORDER);
			statement.setInt(1, order.getId());
			statement.setInt(6, order.getId());
			statement.setString(2, order.getName());
			statement.setString(3, order.getDescription());
			statement.setString(4, order.getAdditionalInformation());
			statement.setInt(5, order.getCustomerId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteOrder(Order order) {
		deleteOrderById(order.getId());
	}

	public void deleteOrderById(int id) {
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(DELETE_ORDER);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Order getOrderById(int id) {
		Order result = new Order();
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(GET_ORDER_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			result = toOrder(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Order getOrderByName(String orderName) {
		Order result = new Order();
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(GET_ORDER_BY_NAME);
			statement.setString(1, orderName);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			result = toOrder(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	

	public List<Order> getAllUncosidered() {
		List<Order> result = new ArrayList<Order>();
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(GET_UNCONSIDERED_ORDERS);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(toOrder(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Order> getAllUncosideredByCustomerId(int id) {
		List<Order> result = new ArrayList<Order>();
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(GET_UNCONSIDERED_ORDERS_BY_CUSTOMER_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(toOrder(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Order> getAllOrdersByCustomerId(int id) {
		List<Order> result = new ArrayList<Order>();
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(GET_ALL_ORDERS_BY_CUSTOMER_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(toOrder(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Order> getAll() {
		List<Order> result = new ArrayList<Order>();
		try {
			PreparedStatement statement = ConnectionManager.getConnection()
					.prepareStatement(GET_ALL_ORDERS);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(toOrder(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Order toOrder(ResultSet resultSet) {
		Order result = new Order();
		try {
			result.setId(resultSet.getInt("id"));
			result.setName(resultSet.getString("name"));
			result.setDescription(resultSet.getString("description"));
			result.setAdditionalInformation(resultSet
					.getString("additional_info"));
			result.setCustomerId(Integer.valueOf(resultSet
					.getString("customer_id")));
			result.setTaskLoads(taskLoadDao.getAllLoadTasksByOrderId(result
					.getId()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
