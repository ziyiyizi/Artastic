package com.javaee.artastic.Artastic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaee.artastic.Artastic.entity.User;
import com.javaee.artastic.Artastic.mapper.UserMapper;

@RestController
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping(value = {"/selectUserById"}, method=RequestMethod.GET)
	public void selectUserById(String id){
		User user = userMapper.selectUserById(Integer.parseInt(id));
		System.out.println(user.getClass());
        //return user;
	}
	
	@RequestMapping(value={"/selectUserByName"}, method=RequestMethod.GET)
    public List<User> selectUserByName(String userName){
        return userMapper.selectUserByName(userName);
    }

    @RequestMapping(value={"/addUser"}, method=RequestMethod.POST)
    public void addUser(User user){
        userMapper.addUser(user);
    }

    @RequestMapping(value={"/updateUser"}, method=RequestMethod.POST)
    public void updateUser(User user){
        userMapper.updateUser(user);
    }

    @RequestMapping(value={"/deleteUser"}, method=RequestMethod.POST)
    public void deleteUser(String id){
        userMapper.deleteUser(Integer.parseInt(id));
    }


}
