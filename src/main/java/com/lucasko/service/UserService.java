package com.lucasko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasko.dao.UserDao;
import com.lucasko.model.Count;
import com.lucasko.model.User;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public User getUser(String username) {
		User user = userDao.getUser(username);
		return user;
	}

	public List<User> getUsers() {
		List<User> users = userDao.getUsers();
		return users;
	}
	
	public void addOrUpdate(User user){
		userDao.saveOrUpdateUser(user);
	}
	
	public void saveUser(User user){
		userDao.saveOrUpdateUser(user);
	}
	
	public int  deleteUser(String  username){
		return userDao.deleteUser(username);
	}
	
	public List<Count> getCount() {
		List<Count> counts = userDao.getCount();
		return counts;
	}
}