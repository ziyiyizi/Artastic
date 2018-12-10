package com.javaee.artastic.Artastic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.artastic.Artastic.dao.ArtworksDao;
import com.javaee.artastic.Artastic.domain.ArtWorkDetails;
import com.javaee.artastic.Artastic.domain.ArtWorkLikes;
import com.javaee.artastic.Artastic.domain.Artworks;
import com.javaee.artastic.Artastic.domain.ArtworksList;
import com.javaee.artastic.Artastic.domain.Clicks;
import com.javaee.artastic.Artastic.domain.Likes;
import com.javaee.artastic.Artastic.service.ArtworksService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ArtWorkController {
	@Autowired
	private ArtworksService artworkService;
	
	@Autowired
	private ArtworksDao artworksDao;
	
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
	
	@RequestMapping(value="/post")
	@ResponseBody
	public ArtworksList getArtworkOne(String postId) {
		int artworkId = Integer.parseInt(postId);
		System.out.println("获取图片详细信息:"+postId);
		ArtworksList artworksList = new ArtworksList();
		ArtWorkDetails artWorkDetails = artworkService.getArtworkDetails(artworkId);
//		artWorkDetails.setLikerslist(artworkService.findLikesList(artworkId));
//		artWorkDetails.setComments(artworkService.findCommentList(artworkId));
		artworksList.setPost(artWorkDetails);
		return artworksList;
		
	}
	
	@RequestMapping(value="/getlikelistandcomments")
	@ResponseBody
	public ArtWorkLikes getlikelistandcomments(@RequestHeader HttpHeaders headers) {
		Clicks clicks = new Clicks();
		
		int artworkId = Integer.valueOf(headers.getFirst("artworkid"));
		int userId = Integer.valueOf(headers.getFirst("userId"));
		clicks.setArtworkId(artworkId);
		clicks.setUserId(userId);
		clicks.setClicktime(new Timestamp(System.currentTimeMillis()));
		artworkService.saveClick(clicks);
		
		return artworkService.getArtworkLikes(artworkId);
	}
	
	@RequestMapping(value="/likerequest")
	public void addLikes(@RequestHeader HttpHeaders headers) {
		int userId = Integer.valueOf(headers.getFirst("userid"));
		int artworkId = Integer.valueOf(headers.getFirst("present"));
		
		if(artworkService.isLike(userId, artworkId) == false) {
			Likes likes = new Likes();
			likes.setArtworkId(artworkId);
			likes.setUserId(userId);
			likes.setLiketime(new Timestamp(System.currentTimeMillis()));
			artworkService.saveLike(likes);
			System.out.println("已添加喜欢");
		}
		
	}
	
	@RequestMapping(value="/artwork/test")
	@ResponseBody
	public void pageable(@RequestParam("pageId")String pageId) {
		int id = Integer.parseInt(pageId);
		Pageable pageable = new PageRequest(id, 5);
		Page<Artworks> page = artworksDao.findAll(pageable);
		//查询结果总行数
		System.out.println(page.getTotalElements());
		  //按照当前分页大小，总页数
		System.out.println(page.getTotalPages());
		  //按照当前页数、分页大小，查出的分页结果集合
		for(Artworks artworks : page.getContent()) {
			System.out.println(artworks.getArtworkName());
		}
	}
}
