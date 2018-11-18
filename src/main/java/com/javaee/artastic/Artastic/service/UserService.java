package com.javaee.artastic.Artastic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaee.artastic.Artastic.entity.User;
import com.javaee.artastic.Artastic.iservice.IUserService;
import com.javaee.artastic.Artastic.mapper.UserMapper;

@Service
public class UserService implements IUserService{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User selectUserById(int id) {
		return userMapper.selectUserById(id);
	}

	@Override
	public List<User> selectUserByName(String userName) {
		// TODO Auto-generated method stub
		return userMapper.selectUserByName(userName);
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userMapper.addUser(user);
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userMapper.updateUser(user);
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userMapper.deleteUser(id);
	}
	
	
}
