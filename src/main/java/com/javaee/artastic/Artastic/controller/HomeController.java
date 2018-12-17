package com.javaee.artastic.Artastic.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@EnableAutoConfiguration
@RestController
public class HomeController {

	
	@RequestMapping(value= {"/index", "/"})
	@ResponseBody
	public ModelAndView gohome() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("webName","Artastic");
		return mv;
	}
	
	@RequestMapping(value= {"/success"})
	@ResponseBody
	public ModelAndView success() {
		ModelAndView mv = new ModelAndView("success");
		return mv;
	}
	
	@RequestMapping(value= {"/community"})
	@ResponseBody
	public ModelAndView community() {
		ModelAndView mv = new ModelAndView("community");
		return mv;
	}
	
	@RequestMapping(value= {"/websockettest/{userName}"})
	@ResponseBody
	public ModelAndView websocket(@PathParam("userName")String userName) {
		ModelAndView mv = new ModelAndView("websocket");
		mv.addObject("userName", userName);
		return mv;
	}

	
}
