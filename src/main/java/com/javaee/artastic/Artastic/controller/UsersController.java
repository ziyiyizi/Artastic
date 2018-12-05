package com.javaee.artastic.Artastic.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.javaee.artastic.Artastic.domain.Params;
import com.javaee.artastic.Artastic.domain.Roles;
import com.javaee.artastic.Artastic.domain.UserRole;
import com.javaee.artastic.Artastic.domain.Users;
import com.javaee.artastic.Artastic.service.UsersService;

@EnableAutoConfiguration
@RestController
@RequestMapping(value="/user")

public class UsersController {
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private UsersService usersService;
	
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
	@RequiresPermissions("user:showAll")
	@ResponseBody
	public List<Users> showAll(){
		List<Users> usersEntities = usersService.findAll();
		return usersEntities;
	}
	
	@RequestMapping(value= {"/byName"})
	@ResponseBody
	public Users getUserByName(@RequestParam("name")String name) {
		Users users = usersService.findByUserName(name);
		return users;
		
	}
	
	@RequestMapping(value="/userRole")
	@ResponseBody
	public List<Roles> getRoles(@RequestParam("userid")int userid){
		return usersService.findRoleList(userid);
	}
}
