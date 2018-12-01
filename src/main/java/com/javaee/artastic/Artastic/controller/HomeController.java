package com.javaee.artastic.Artastic.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@EnableAutoConfiguration
@RestController
public class HomeController {

	
	@RequestMapping(value="/home")
	@ResponseBody
	public ModelAndView gohome() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("webName","Artastic");
		return mv;
	}
	
	
	
}