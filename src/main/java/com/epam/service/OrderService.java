package com.epam.service;

import java.util.List;

import com.epam.bean.Order;
import com.epam.dao.OrderDao;

public class OrderService {

	private OrderDao orderDao;

	public OrderService() {
		orderDao = new OrderDao();
	}

	public Order getOrderById(int id) {
		return orderDao.getOrderById(id);
	}

	public Order getOrderByName(String orderName) {
		return orderDao.getOrderByName(orderName);
	}

	public List<Order> getAll() {
		return orderDao.getAll();
	}

	public List<Order> getAllOrdersByCustomerId(int id) {
		return orderDao.getAllOrdersByCustomerId(id);
	}

	public List<Order> getAllUncosideredByCustomerId(int id) {
		return orderDao.getAllUncosideredByCustomerId(id);
	}

	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
	}

	public void updateOrder(Order order) {
		orderDao.updateOrder(order);
	}

	public void deleteOrder(Order order) {
		orderDao.deleteOrder(order);
	}

	public void deleteOrderById(int id) {
		orderDao.deleteOrderById(id);
	}

	public List<Order> getAllUncosidered() {
		return orderDao.getAllUncosidered();
	}
}
