package com.javaee.artastic.Artastic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javaee.artastic.Artastic.dao.UsersDao;
import com.javaee.artastic.Artastic.domain.UserRole;
import com.javaee.artastic.Artastic.domain.Users;
import com.javaee.artastic.Artastic.entity.Params;

@EnableAutoConfiguration
@RestController
@RequestMapping(value="/user")

public class UsersController {
	@Autowired
	private UsersDao usersdao;
	
	@RequestMapping(value="/login")	
	@ResponseBody
	public Users testlogin(@RequestBody Params params) {
		System.out.println(params.getUsername());
		Users user = new Users();
		user.setUserId(1234);
		user.setUserName(params.getUsername());
		return user;
		
	}
	
	@RequestMapping(value={"/showAll"})
	@ResponseBody
	public List<Users> showAll(){
		List<Users> usersEntities = usersdao.findAll();
		for(Users usersEntity : usersEntities) {
			
			System.out.println(usersEntity.getUserName());
		}
		
		return usersEntities;
	}
}
