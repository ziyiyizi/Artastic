package com.javaee.artastic.Artastic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaee.artastic.Artastic.entity.User;
import com.javaee.artastic.Artastic.mapper.UserMapper;
import com.javaee.artastic.Artastic.service.UserService;

@RestController
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"/selectId"}, method=RequestMethod.GET)
	public User selectId(String id){
		User user = userService.selectUserById(Integer.parseInt(id));
        return user;
	}
	
	@RequestMapping(value={"/selectName"}, method=RequestMethod.GET)
    public List<User> selectName(String userName){
        return userService.selectUserByName(userName);
    }

    @RequestMapping(value={"/add"}, method=RequestMethod.POST)
    public void add(User user){
        userService.addUser(user);
    }

    @RequestMapping(value={"/update"}, method=RequestMethod.POST)
    public void update(User user){
        userService.updateUser(user);
    }

    @RequestMapping(value={"/delete"}, method=RequestMethod.POST)
    public void delete(String id){
        userService.deleteUser(Integer.parseInt(id));
    }


}
