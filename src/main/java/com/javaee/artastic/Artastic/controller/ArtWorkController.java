package com.javaee.artastic.Artastic.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.javaee.artastic.Artastic.dao.ArtworksDao;
import com.javaee.artastic.Artastic.domain.ArtWorkDetails;
import com.javaee.artastic.Artastic.domain.ArtWorkLikes;
import com.javaee.artastic.Artastic.domain.Artworks;
import com.javaee.artastic.Artastic.domain.ArtworksList;
import com.javaee.artastic.Artastic.domain.Clicks;
import com.javaee.artastic.Artastic.domain.Comments;
import com.javaee.artastic.Artastic.domain.Error;
import com.javaee.artastic.Artastic.domain.Likes;
import com.javaee.artastic.Artastic.service.ArtworksService;
import com.javaee.artastic.Artastic.utils.ExceptionUtil;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class ArtWorkController {
	@Autowired
	private ArtworksService artworkService;
	
//	@RequestMapping(value="/community/{postType}")
//	@ResponseBody
//	public ModelAndView showArtwork(@PathVariable("postType")String postType) {
//		return new ModelAndView("community");
//	}
	
	@RequestMapping(value="/getPosts")
	@ResponseBody
	public ArtworksList getArtWorksAll(@RequestHeader HttpHeaders headers) {
		String typeSort = headers.getFirst("present");
		if(typeSort.equals("popular")) {
			return getArtworkAllLikeSort();
		} else if (typeSort.equals("latest")) {
			return getArtworkAllTimeSort();
		} else {
			return getArtworkAllRandSort();
		}
	}
	
	public ArtworksList getArtworkAllLikeSort() {
		ArtworksList artworksList = new ArtworksList();
		try {
			Pageable pageable = new PageRequest(0, 10);
			Page<Integer> page = artworkService.findAllLikeSort(pageable);
			List<ArtWorkDetails> artWorkDetails = new ArrayList<>();
			List<Integer> artworkIds = page.getContent();
			
			for(Integer integer : artworkIds) {
				artWorkDetails.add(artworkService.getArtworkDetails(integer));
			}
			
			artworksList.setPosts(artWorkDetails);
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		
		return artworksList;

	}
	public ArtworksList getArtworkAllRandSort() {

		ArtworksList artworksList = new ArtworksList();
		try {
			Pageable pageable = new PageRequest(0, 10);
			List<ArtWorkDetails> artWorkDetails = new ArrayList<>();
			Page<Integer> page = artworkService.findAllRandSort(pageable);
			List<Integer> artworkIds = page.getContent();
			
			for(Integer integer : artworkIds) {
				artWorkDetails.add(artworkService.getArtworkDetails(integer));
			}
			
			artworksList.setPosts(artWorkDetails);
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		
		return artworksList;
	}
	
	public ArtworksList getArtworkAllTimeSort() {
		ArtworksList artworksList = new ArtworksList();
		try {
			Pageable pageable = new PageRequest(0, 10);
			Page<Integer> page = artworkService.findAllTimeSort(pageable);
			List<ArtWorkDetails> artWorkDetails = new ArrayList<>();
			List<Integer> artworkIds = page.getContent();
			
			for(Integer integer : artworkIds) {
				artWorkDetails.add(artworkService.getArtworkDetails(integer));
			}
			
			artworksList.setPosts(artWorkDetails);
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		
		return artworksList;
		
	}
	
	
	@RequestMapping(value="/post/{postId}")
	@ResponseBody
	public ModelAndView Post(@PathVariable("postId")String postId) {
		return new ModelAndView("community");
	}
	
	@RequestMapping(value="/getpost")
	@ResponseBody
	public ArtworksList getArtworkOne(@RequestHeader HttpHeaders headers) {
		ArtworksList artworksList = new ArtworksList();
		try {
			System.out.println(headers.getFirst("present"));
			int artworkId = Integer.parseInt(headers.getFirst("present"));
			
			ArtWorkDetails artWorkDetails = artworkService.getArtworkDetails(artworkId);
			artworksList.setPost(artWorkDetails);
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		return artworksList;
	}
	
	@RequestMapping(value="/getlikelistandcomments")
	@ResponseBody
	public ArtWorkLikes getlikelistandcomments(@RequestHeader HttpHeaders headers) {
		ArtWorkLikes artWorkLikes = new ArtWorkLikes();
		try {
			int artworkId = Integer.valueOf(headers.getFirst("artworkid"));
			int userId = Integer.valueOf(headers.getFirst("userId"));
			Clicks clicks = new Clicks();
			clicks.setArtworkId(artworkId);
			clicks.setUserId(userId);
			clicks.setClicktime(new Timestamp(System.currentTimeMillis()));
			artworkService.saveClick(clicks);
			artWorkLikes = artworkService.getArtworkLikes(artworkId);
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		
		return artWorkLikes;
	}
	
	@RequestMapping(value="/likerequest")
	@ResponseBody
	public Error addLikes(@RequestHeader HttpHeaders headers) {
		Error error = new Error();
		error.setError(false);
		try {
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
			
		}catch (Exception e) {
			error.setError(true);
		}
		
		return error;
	}
	
	@RequestMapping(value="/artwork/test")
	@ResponseBody
	public void pageable(@RequestParam("pageId")String pageId) {
		int id = Integer.parseInt(pageId);
		Pageable pageable = new PageRequest(id, 5);
		Page<Artworks> page = artworkService.findAll(pageable);
		//查询结果总行数
		System.out.println(page.getTotalElements());
		  //按照当前分页大小，总页数
		System.out.println(page.getTotalPages());
		  //按照当前页数、分页大小，查出的分页结果集合
		for(Artworks artworks : page.getContent()) {
			System.out.println(artworks.getArtworkName());
		}
	}
	
	@RequestMapping(value="makecomment")
	public Error makeComment(HttpServletRequest request, @RequestHeader HttpHeaders headers) {
		Error error = new Error();
		error.setError(false);
		try {
			String commentorName = headers.getFirst("username");
			
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
			String responseTo = mRequest.getParameter("responseTo");
			String comment = mRequest.getParameter("comment");
			int artworkId = Integer.parseInt(mRequest.getParameter("artworkId"));
			
			Comments comments = new Comments();
			comments.setArtworkId(artworkId);
			comments.setComment(comment);
			comments.setCommentorName(commentorName);
			comments.setUserName(responseTo);
			comments.setCommentTime(new Timestamp(System.currentTimeMillis()));
			artworkService.saveComment(comments);
		}catch (Exception e) {
			// TODO: handle exception
			error.setError(true);
		}
		return error;
	}
	
	@RequestMapping(value="search/{searchType}/{searchKey}")
	@ResponseBody
	public ModelAndView search(@PathVariable("searchType")String searchType, @PathVariable("searchKey")String searchKey) {
		return new ModelAndView("community");
	}
	
	@RequestMapping(value="getsearch")
	@ResponseBody
	public ArtworksList searchArtworks(@RequestHeader HttpHeaders headers) {
		
		ArtworksList artworksList = new ArtworksList();
		try {
			
			String present = URLDecoder.decode(headers.getFirst("present"), "UTF-8");
			String[] strings = present.split("/");
			String searchType = strings[1];
			String searchKey = strings[2];
			Pageable pageable = new PageRequest(0, 10);
			Page<Integer> page = artworkService.findBySearchKey(searchKey, pageable);
//			Page<Integer> page = artworkService.findBySearchAll(searchKey, pageable);
			
			List<ArtWorkDetails> artWorkDetails = new ArrayList<>();
			List<Integer> artworkIds = page.getContent();
			
			for(Integer integer : artworkIds) {
				artWorkDetails.add(artworkService.getArtworkDetails(integer));
			}
			artworksList.setPosts(artWorkDetails);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExceptionUtil.handleException(e);
		}
		
		return artworksList;
		
	}
}
