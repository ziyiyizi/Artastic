package com.javaee.artastic.Artastic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaee.artastic.Artastic.domain.ArtworksList;
import com.javaee.artastic.Artastic.service.UsersService;
import com.javaee.artastic.Artastic.utils.ExceptionUtil;

@Controller
public class NotificationController {

	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value="getnotification")
	@ResponseBody
	public ArtworksList pullNotification(@RequestHeader HttpHeaders headers){
		ArtworksList artworksList = new ArtworksList();
		try {
			String receiverName = headers.getFirst("username");
			if(!receiverName.equals("null")) {
				artworksList.setNotification(usersService.findByReceiverName(receiverName));
				usersService.updateNotification(receiverName);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		
		return artworksList;
	}
	
	@RequestMapping(value="fetchnotification")
	@ResponseBody
	public ArtworksList fetchNotification(@RequestHeader HttpHeaders headers) {
		ArtworksList artworksList = new ArtworksList();
		try {
			String receiverName = headers.getFirst("username");
			if(!receiverName.equals("null")) {
				artworksList.setNotifyNum(usersService.countNotifyNum(receiverName));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		
		return artworksList;
	}
}
