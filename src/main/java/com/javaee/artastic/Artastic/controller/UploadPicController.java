package com.javaee.artastic.Artastic.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.artastic.Artastic.service.impl.UploadPicService;

@RestController
@RequestMapping("/upload")
public class UploadPicController {


	@Autowired
	private UploadPicService uploadPicService;
	
    @RequestMapping(value="/test", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> headImgUpload(HttpServletRequest request, HttpServletResponse response) {

    	return uploadPicService.uploadFile(request);
    }

}
