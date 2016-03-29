package com.lucasko.dao;

import java.util.List;

import com.lucasko.model.Count;
import com.lucasko.model.User;

public interface UserDao {

	User getUser(String username);
	List<User> getUsers();
	public void saveOrUpdateUser(User user);
	public int deleteUser (String username);
	public List<Count> getCount();

}