package com.javaee.artastic.Artastic.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@EnableAutoConfiguration
@RestController
public class HomeController {

	
	@RequestMapping(value= {"/index", "/"})
	public ModelAndView gohome() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("webName","Artastic");
		return mv;
	}
	
	@RequestMapping(value= {"/user/login", "/login"},method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value= {"/success"})
	public ModelAndView success() {
		ModelAndView mv = new ModelAndView("success");
		return mv;
	}
	
	@RequestMapping(value= {"/community"})
	public ModelAndView community() {
		ModelAndView mv = new ModelAndView("community");
		return mv;
	}
	
	@RequestMapping(value="/community/{postType}")
	public ModelAndView showArtwork(@PathVariable("postType")String postType) {
		return new ModelAndView("community");
	}
	
	@RequestMapping(value="/lab/{postType}")
	public ModelAndView showLab(@PathVariable("postType")String postType) {
		return new ModelAndView("community");
	}
	
	@RequestMapping(value="/post/{postId}")
	public ModelAndView Post(@PathVariable("postId")String postId) {
		return new ModelAndView("community");
	}
	
	@RequestMapping(value="search/{searchType}/{searchKey}")
	public ModelAndView search(@PathVariable("searchType")String searchType, @PathVariable("searchKey")String searchKey) {
		return new ModelAndView("community");
	}

}
