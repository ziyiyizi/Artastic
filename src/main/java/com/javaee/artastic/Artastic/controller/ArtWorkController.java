package com.javaee.artastic.Artastic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.artastic.Artastic.domain.Artworks;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
public class ArtWorkController {
	
	@RequestMapping(value="/post")
	@ResponseBody
	public List<Artworks> getArtWorks(){
		Artworks artwork1 = new Artworks();
		artwork1.setArtistId(1);
		artwork1.setArtworkDescription("first artword");
		artwork1.setArtworkId(1);
		artwork1.setArtworkName("pixiv");
		artwork1.setUploadtime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		Artworks artwork2= new Artworks();
		artwork2.setArtistId(2);
		artwork2.setArtworkDescription("second artword");
		artwork2.setArtworkId(2);
		artwork2.setArtworkName("pixiv2");
		artwork2.setUploadtime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		List<Artworks> artworks = new ArrayList<>();
		artworks.add(artwork1);
		artworks.add(artwork2);
		return artworks;
	}
}
