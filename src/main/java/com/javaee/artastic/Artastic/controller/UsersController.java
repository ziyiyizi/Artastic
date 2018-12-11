package com.javaee.artastic.Artastic.controller;

import java.sql.Timestamp;
import java.util.List;


import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javaee.artastic.Artastic.domain.Error;
import com.javaee.artastic.Artastic.domain.Follow;
import com.javaee.artastic.Artastic.domain.Params;
import com.javaee.artastic.Artastic.domain.Roles;
import com.javaee.artastic.Artastic.domain.Users;
import com.javaee.artastic.Artastic.service.UsersService;


@EnableAutoConfiguration
@RestController
//@RequestMapping(value="/user")

public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value="/user/login",method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/user/login",method = RequestMethod.POST)
	@ResponseBody
	public Params login(@RequestBody Params param, HttpResponse response, HttpSession session) throws Exception {

        //ModelAndView mView = new ModelAndView("success");
        String username = param.getUsername();
        String pwd = param.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username,pwd);
        Subject subject = SecurityUtils.getSubject();
        try {
        	subject.login(token);
        	session.setAttribute("user", subject.getPrincipal());
        	Users users = usersService.findByUserName(username);
        	param.setUserId(users.getUserId());
        	System.out.println("登录成功");
        	return param;
        }catch (Exception e) {
			// TODO: handle exception
        	String msg = null;
        	if(e instanceof UnknownAccountException) {
        		System.out.println("账户不存在");
                msg = "账户不存在或密码不正确";
        	} else if(e instanceof IncorrectCredentialsException) {
        		System.out.println("密码不正确");
                msg = "账户不存在或密码不正确";
        	} else {
        		System.out.println("其他异常");
                msg = "其他异常";
			}
        	System.out.println(msg);
//            mView.addObject("msg", msg);	
//            mView.addObject("error", true);
            return null;
		}
        
    }
	
	@RequestMapping(value={"/user/showAll"})
	@RequiresPermissions("user:showAll")
	@ResponseBody
	public List<Users> showAll(){
		List<Users> usersEntities = usersService.findAll();
		return usersEntities;
	}
	
	@RequestMapping(value= {"/user/byName"})
	@ResponseBody
	public Users getUserByName(@RequestParam("name")String name) {
		Users users = usersService.findByUserName(name);
		return users;
		
	}
	
	@RequestMapping(value="/user/userRole")
	@ResponseBody
	public List<Roles> getRoles(@RequestParam("userid")int userid){
		return usersService.findRoleList(userid);
	}
	
	
	@RequestMapping(value="/user/addFollow")
	@ResponseBody
	public Error addFollow(@RequestHeader HttpHeaders headers) {
		Error error = new Error();
		error.setError(false);
		try {
			Follow follow = new Follow();
			follow.setArtistId(Integer.parseInt(headers.getFirst("artistId")));
			follow.setFollowerId(Integer.parseInt(headers.getFirst("userId")));
			follow.setFollowtime(new Timestamp(System.currentTimeMillis()));
			//usersService.saveFollow(follow);
		}catch (Exception e) {
			// TODO: handle exception
			error.setError(true);
		}
		return error;
	}
}
