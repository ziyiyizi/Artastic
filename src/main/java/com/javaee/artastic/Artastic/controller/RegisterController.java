package com.javaee.artastic.Artastic.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.javaee.artastic.Artastic.domain.Users;
import com.javaee.artastic.Artastic.service.UsersService;
import com.javaee.artastic.Artastic.service.impl.MailService;
import com.javaee.artastic.Artastic.utils.Md5Util;

@EnableAutoConfiguration
@RestController
@RequestMapping(value="/Register")
public class RegisterController {
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@RequestMapping(value= {"/reg"})
	public ModelAndView register() {
		ModelAndView mView = new ModelAndView("testreg");
		return mView;
	}
	
	@RequestMapping(value= {"/test"}, method=RequestMethod.POST)
	public void registerUser(@RequestParam("email")String email, @RequestParam("username")String username, @RequestParam("pwd")String pwd) {
		String token = UUID.randomUUID().toString();
		Timestamp registerTime = new Timestamp(System.currentTimeMillis());
		Timestamp tokenTime = new Timestamp(System.currentTimeMillis()+1000*3600*24);
//检查邮箱用户名是否重复
		//控制邮件在一定时间内只发送一次
		Users users = new Users();
		users.setRegistertime(registerTime);
		users.setTokenTime(tokenTime);
		users.setUserMail(email);
		users.setUserName(username);
		users.setUserPassword(pwd);
		users.setUserState("0");
		users.setUserToken(token);
		
		//检查此处id是否有问题
		Users users2 = usersService.save(users);
		int userId = users2.getUserId();
		
		if(users2 != null) {
			
			String registerLink="http://localhost:8080/Register/check?userId="+Md5Util.convertMD5(String.valueOf(userId))
			+"&token="+Md5Util.string2MD5(token)
			+"&tokenTime="+Md5Util.string2MD5(tokenTime.toString());
			Context context = new Context();
			context.setVariable("email", email);
		    context.setVariable("registerLink", registerLink);
			String emailContent = templateEngine.process("activate", context);
			
			mailService.sendHtmlMail(email,"主题：激活邮件",emailContent);
		}

		
	}
	
	@RequestMapping(value= {"/check"})
	public String registerCheck(HttpServletRequest request) {
		System.out.println("进入激活检查");
		//检查用户状态是否需要激活
		String userIdStr = Md5Util.convertMD5(request.getParameter("userId"));
		int userId = Integer.valueOf(userIdStr);
		String token = request.getParameter("token");
		String tokenTime = usersService.findTokenTimeByUserId(userId);
		String nowTime = new Timestamp(System.currentTimeMillis()).toString();
		int result = tokenTime.compareTo(nowTime);
		if(result == 1) {
			String realToken = Md5Util.string2MD5(usersService.findUserTokenByUserId(userId));
			if(token.equals(realToken)) {
				usersService.updateUserStateByUserId(userId, "1");
				System.out.println("激活成功");
				return "redirect:/";
			}else {
				
			}
			
		}else {
			usersService.updateUserTokenByUserId(userId, null);
		}
		return "redirect:/error";
	}

}
