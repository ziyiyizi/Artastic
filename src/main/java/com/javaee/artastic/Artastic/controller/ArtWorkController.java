package com.javaee.artastic.Artastic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.artastic.Artastic.domain.ArtWorkDetails;
import com.javaee.artastic.Artastic.domain.Artworks;
import com.javaee.artastic.Artastic.domain.ArtworksList;
import com.javaee.artastic.Artastic.service.ArtworksService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ArtWorkController {
	@Autowired
	private ArtworksService artworkService;
	
	@RequestMapping(value="/post")
	@ResponseBody
	public List<Artworks> getArtWorks(){
		Artworks artwork1 = new Artworks();
		artwork1.setArtistId(1);
		artwork1.setArtworkDescription("first artword");
		artwork1.setArtworkId(1);
		artwork1.setArtworkName("pixiv");
		artwork1.setUploadtime(new Timestamp(System.currentTimeMillis()));
		
		Artworks artwork2= new Artworks();
		artwork2.setArtistId(2);
		artwork2.setArtworkDescription("second artword");
		artwork2.setArtworkId(2);
		artwork2.setArtworkName("pixiv2");
		artwork2.setUploadtime(new Timestamp(System.currentTimeMillis()));
		List<Artworks> artworks = new ArrayList<>();
		artworks.add(artwork1);
		artworks.add(artwork2);
		return artworks;
	}
	
	@RequestMapping(value="/artwork/showOne")
	@ResponseBody
	public ArtWorkDetails showOne(@RequestParam("artworkId")int artworkId) {
		
		return artworkService.getArtworkDetails(artworkId);
	}
	
	@RequestMapping(value="/getPosts")
	@ResponseBody
	public ArtworksList getArtWorksAll() {
		List<ArtWorkDetails> artWorkDetails = new ArrayList<>();
		List<Artworks> artworks = artworkService.findAll();
		
		for(Artworks artwork : artworks) {
			artWorkDetails.add(artworkService.getArtworkDetails(artwork.getArtworkId()));
			
		}
		ArtworksList artworksList = new ArtworksList();
		artworksList.setPosts(artWorkDetails);
		return artworksList;
	}
}
