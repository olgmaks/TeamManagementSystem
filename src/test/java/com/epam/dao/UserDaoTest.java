package com.epam.dao;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.bean.User;

public class UserDaoTest {
	
	private static UserDao userDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userDao = new UserDao();
	}


	@Test
	public void testGet() {
		System.out.println(userDao.getUserById(1));
	}
	
	@Test
	public void testGetAll() {
		
	List<User> users = userDao.getAll();
	System.out.println("list size : " + users.size() );
		for (User user : users) {
			System.out.println(user);
		}

	}
	
	public void testSave () {}
	
	public void testUpdate () {}
	
	public void testDelete () {}

}
