package com.javaee.artastic.Artastic.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	@RequestMapping(value= {"/login"})
	@ResponseBody
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	@RequestMapping(value="/loginUser")
	public ModelAndView loginUser(HttpServletRequest request, HttpSession session) throws Exception {

        ModelAndView mView = new ModelAndView("index");
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");
        UsernamePasswordToken token = new UsernamePasswordToken(username,pwd);
        Subject subject = SecurityUtils.getSubject();
        try {
        	subject.login(token);
        	session.setAttribute("user", subject.getPrincipal());
        	return mView;
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
            mView.setViewName("login");
            mView.addObject("msg", msg);	
            return mView;
		}
        
    }
	
	@RequestMapping(value= {"/success"})
	@ResponseBody
	public ModelAndView success() {
		ModelAndView mv = new ModelAndView("success");
		return mv;
	}
	
	@RequestMapping(value= {"/community", "/error"})
	@ResponseBody
	public ModelAndView community() {
		ModelAndView mv = new ModelAndView("community");
		return mv;
	}

	
}
