package com.epam.service;

import java.util.List;

import com.epam.bean.User;
import com.epam.dao.UserDao;

public class UserService {

	private UserDao userDao;

	public UserService() {
		userDao = new UserDao();
	}

	public User getUserById(int id) {
		return userDao.getUserById(id);
	}
	
	public User getUserByEmail(String email){
		return userDao.getUserByEmail(email);
	}
	
	public void saveUser (User user) {
		userDao.saveUser(user);
	}
	
	public List<User> getAll() {
		return userDao.getAll();
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
		
	}
}
