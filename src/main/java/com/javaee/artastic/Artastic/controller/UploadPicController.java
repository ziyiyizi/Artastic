package com.javaee.artastic.Artastic.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.javaee.artastic.Artastic.service.impl.UploadPicService;

@RestController
@RequestMapping("/upload")
public class UploadPicController {


	@Autowired
	private UploadPicService uploadPicService;
	
	@RequestMapping(value="/uploadpic")
	@ResponseBody
	public ModelAndView uploadpic() {
		ModelAndView mv = new ModelAndView("uploadpic");
		return mv;
	}
	
    @RequestMapping(value="/test", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> headImgUpload(HttpServletRequest request, @RequestHeader HttpHeaders headers) {
//    	System.out.println(param.getTitle());
//    	System.out.println(param.getDescription());
//    	System.out.println(param.getTags());

    	return uploadPicService.uploadFile(request, headers);
    }

}
