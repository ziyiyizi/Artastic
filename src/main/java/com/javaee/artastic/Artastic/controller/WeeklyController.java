package com.javaee.artastic.Artastic.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import com.javaee.artastic.Artastic.domain.ArtworksList;
import com.javaee.artastic.Artastic.domain.Weekly;
import com.javaee.artastic.Artastic.service.ArtworksService;
import com.javaee.artastic.Artastic.service.UsersService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeeklyController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ArtworksService artworksService;
	
	@RequestMapping("/getweekly")
	@ResponseBody
	public ArtworksList getWeekly(@RequestHeader HttpHeaders headers) {
		ArtworksList artworksList = new ArtworksList();
		
		try {
			Weekly weekly = new Weekly();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			Date startDay = new Date(System.currentTimeMillis()-3600l*24*7*1000);
			Date endDay = new Date(System.currentTimeMillis());
			String start = formatter.format(startDay);
			String end = formatter.format(endDay);
			
			weekly.setTags(artworksService.findTagListPopular(start, end));
			
			Object[] artworkdetails = artworksService.findArtworkWeekly(start, end);
			
			if(artworkdetails != null) {
				
				weekly.setArtworkId(Integer.parseInt(String.valueOf(artworkdetails[0])));
				weekly.setArtworkName((String)artworkdetails[1]);
				int artistId = Integer.parseInt(String.valueOf(artworkdetails[2]));
				weekly.setFileURL((String)artworkdetails[3]);
				weekly.setArtworkviews(Integer.parseInt(String.valueOf(artworkdetails[4])));
				
				Map<String, Object> userNameAndIcon = usersService.findNameAndIconByUserId(artistId);
				
				weekly.setArtistName((String)userNameAndIcon.get("name"));
				weekly.setIconURL((String)userNameAndIcon.get("icon"));
				weekly.setFrenzy(usersService.countFollows(artistId));
			}

			artworksList.setWeekly(weekly);
		} catch (Exception e) {
			// TODO: handle exception
			artworksList.setError(true);
		}
		return artworksList;
	}
}
