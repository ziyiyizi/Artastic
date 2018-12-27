package com.javaee.artastic.Artastic.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.artastic.Artastic.domain.ArtworksList;
import com.javaee.artastic.Artastic.service.impl.UploadPicService;

@RestController
@RequestMapping("/upload")
public class UploadPicController {

	@Autowired
	private UploadPicService uploadPicService;
	
    @RequestMapping(value="/test", method = RequestMethod.POST)
    @ResponseBody
    public ArtworksList headImgUpload(HttpServletRequest request, @RequestHeader HttpHeaders headers) {

    	return uploadPicService.uploadFile(request, headers);
    }

}
