package com.javaee.artastic.Artastic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.artastic.Artastic.domain.ArtWorkDetails;
import com.javaee.artastic.Artastic.domain.ArtWorkLikes;
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
	
	@RequestMapping(value="/getlikelistandcomments")
	@ResponseBody
	public ArtWorkLikes getlikelistandcomments(@RequestHeader HttpHeaders headers) {
		int artworkId = Integer.valueOf(headers.getFirst("artworkid"));
		return artworkService.getArtworkLikes(artworkId);
	}
}
