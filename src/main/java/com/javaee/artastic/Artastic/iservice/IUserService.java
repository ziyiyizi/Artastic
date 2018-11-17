package com.javaee.artastic.Artastic.iservice;

import java.util.List;

import com.javaee.artastic.Artastic.entity.User;

public interface IUserService {
	public User selectUserById(int id);
	public List<User> selectUserByName(String userName);
	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
}
