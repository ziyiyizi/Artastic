package com.javaee.artastic.Artastic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.artastic.Artastic.domain.ArtWorkDetails;
import com.javaee.artastic.Artastic.domain.Artworks;
import com.javaee.artastic.Artastic.service.ArtworksService;
import com.javaee.artastic.Artastic.service.UsersService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
public class ArtWorkController {
	@Autowired
	private ArtworksService artworkService;
	
	@Autowired
	private UsersService userService;
	
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
	
	@RequestMapping(value="/artwork/show")
	@ResponseBody
	public ArtWorkDetails show(@RequestParam("artworkId")int artworkId) {
		ArtWorkDetails artWorkDetails = new ArtWorkDetails();
		Artworks artworks = artworkService.findByArtworkId(artworkId);
		int userId = artworks.getArtistId();
		artWorkDetails.setArtworkId(artworkId);
		artWorkDetails.setArtworkName(artworks.getArtworkName());
		artWorkDetails.setArtistId(userId);
		artWorkDetails.setArtistName(userService.findUserNameByUserId(userId));
		artWorkDetails.setDate(artworks.getUploadtime());
		artWorkDetails.setComments(artworkService.findCommentList(artworkId));
		artWorkDetails.setFrenzy(artworkService.countLikes(artworkId));
		artWorkDetails.setLikes(artworkService.findLikesList(artworkId));
		artWorkDetails.setTags(artworkService.findTagList(artworkId));
		
		return artWorkDetails;
	}
}
