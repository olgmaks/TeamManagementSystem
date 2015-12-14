package com.epam.dao;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.bean.Order;

public class OrderDaoTest {

	private static OrderDao orderDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDao = new OrderDao();
	}

	// @Test
	public void testGet() {
		System.out.println(orderDao.getOrderById(1));
	}

//	@Test
	public void testSave() {
		Order order = new Order(3, "Mersedes-Benz", "Glass manufacturing",
				"Location : Kiev", 7);
		orderDao.saveOrder(order);
	}

//	@Test
	public void testUpdate() {
		Order order = new Order(4, "Mersedes-Benz", "Glass manufacturing",
				"Location : Kiev", 7);
		orderDao.updateOrder(order);
	}
	
	@Test
	public void testDelete() {
		orderDao.deleteOrderById(4); 
	}

	// @Test
	public void testGetAll() {
		List<Order> list = orderDao.getAll();
		for (Order order : list) {
			System.out.println(order);
		}
	}

}
