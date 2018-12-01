package com.javaee.artastic.Artastic.controller;

import java.util.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.javaee.artastic.Artastic.dao.UsersDao;
import com.javaee.artastic.Artastic.service.MailService;
import com.sun.mail.handlers.text_html;

@EnableAutoConfiguration
@RestController
@RequestMapping(value="/Register")
public class RegisterController {
	@Autowired
	private MailService mailService;
	
//	@Autowired
//	private UsersMapper usersMapper;
//	
//	@Autowired
//	private TemplateEngine templateEngine;
//	
//	@RequestMapping(value= {"/reg"})
//	public ModelAndView register() {
//		ModelAndView mView = new ModelAndView("testreg");
//		return mView;
//	}
//	
//	@RequestMapping(value= {"/test"}, method=RequestMethod.POST)
//	public void registerUser(@RequestParam("email")String email, @RequestParam("username")String username, @RequestParam("pwd")String pwd) {
//		
//		Calendar calendar = Calendar.getInstance();
//		Date registerTime = new Date(calendar.getTimeInMillis());
//		Date tokenTime = new Date(calendar.getTimeInMillis()+1000*3600*24);
//
//		Users users = new Users();
//		users.setRegistertime(registerTime);
//		users.setTokenTime(tokenTime);
//		users.setUserId(6);
//		users.setUserMail(email);
//		users.setUserName(username);
//		users.setUserPassword(pwd);
//		users.setUserState(Byte.valueOf("0"));
//		
//		String token = "dfgdfhfdd";
//		int result = usersMapper.insert(users);
//		if(result > 0) {
//			String registerLink="http://localhost:8080/Register/check/userid="+"6"+"/token="+token;
//			Context context = new Context();
//		    context.setVariable("registerLink", registerLink);
//			String emailContent = templateEngine.process("activate", context);
//			
//			mailService.sendHtmlMail(email,"主题：激活邮件",emailContent);
//		}
//
//		
//	}
	
	@RequestMapping(value= {"/check"})
	public void registerCheck() {
		
	}

}
