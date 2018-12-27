package com.javaee.artastic.Artastic.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;

import com.javaee.artastic.Artastic.domain.Params;
import com.javaee.artastic.Artastic.domain.Users;
import com.javaee.artastic.Artastic.service.UsersService;
import com.javaee.artastic.Artastic.service.impl.MailService;
import com.javaee.artastic.Artastic.utils.ExceptionUtil;
import com.javaee.artastic.Artastic.utils.Md5Util;

@Controller
public class RegisterController {
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
	@ResponseBody
	public Params registerUser(@RequestBody Params params) {
		
		params.setError(false);
		try {
			String email = params.getEmail();
			String username = params.getUsername();
			String pwd = params.getPassword();
			String sex = params.getSex();
			//检查邮箱用户名是否重复 //检查邮箱格式
			if(usersService.isNameOrMailExists(username, email) == true) {
				params.setError(true);
				params.setErrorMsg("The name or email is already exists!");
				return params;
			}
			
			//生成token
			String token = UUID.randomUUID().toString();
			Timestamp registerTime = new Timestamp(System.currentTimeMillis());
			Timestamp tokenTime = new Timestamp(System.currentTimeMillis() + 1000 * 3600 * 24);
	
			//控制邮件在一定时间内只发送一次
			Users users = new Users();
			users.setUserName(username);
			users.setUserPassword(pwd);	
			users.setUserMail(email);
			users.setUserSex(sex);
			users.setRegistertime(registerTime);
			users.setUserToken(token);
			users.setTokenTime(tokenTime);
			users.setUserState("0");
			users.setUserIcon("/a60613b465f2dd5c6f5848d3feb40ffd.jpg");
			
			String[] emailsplit = email.split("@");
			if(emailsplit[1].contains("root")) {
				users.setUserState("1");
			}
			
			//检查此处id是否有问题
			Users users2 = usersService.save(users);
			
			if(users2 != null) {
				int userId = users2.getUserId();
				params.setUserId(userId);
				params.setIconURL(users2.getUserIcon());
				
				if(users2.getUserState().equals("0")) {
					String registerLink="http://localhost:8080/Register/check?userId="
							+Md5Util.convertMD5(String.valueOf(userId))
							+"&token="+Md5Util.string2MD5(token);
					Context context = new Context();
					context.setVariable("email", email);
				    context.setVariable("registerLink", registerLink);
				    String subject = "theme:Click to activate your Artastic account!";
					
					mailService.sendHtmlMail(email, subject, "activate", context);
				}
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			params.setError(true);
		}
		return params;

	}
	
	@RequestMapping(value= {"/Register/check"})
	public String registerCheck(HttpServletRequest request) {
		System.out.println("进入激活检查");
		//检查用户状态是否需要激活
		
		try {
			String userIdStr = Md5Util.convertMD5(request.getParameter("userId"));
			int userId = Integer.valueOf(userIdStr);
			
			Users users = usersService.findByUserId(userId);
			if(users != null && users.getUserState().equals("0")) {
				String token = request.getParameter("token");
				String tokenTime = users.getTokenTime().toString();
				String nowTime = new Timestamp(System.currentTimeMillis()).toString();
				
				int result = tokenTime.compareTo(nowTime);
				if(result == 1) {
					String realToken = Md5Util.string2MD5(users.getUserToken());
					if(token.equals(realToken)) {
						usersService.updateUserStateByUserId(userId, "1");
						System.out.println("success");
						return "redirect:/";
					}else {
						System.out.println("token is incorrect!");
					}
					
				}else {
					//重新发送激活邮件
					System.out.println("token is overdue, please send mail again!");
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		
		return "redirect:/error";
	}

}
