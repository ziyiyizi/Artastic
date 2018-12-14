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
import com.javaee.artastic.Artastic.domain.ChartData;
import com.javaee.artastic.Artastic.domain.Clicks;
import com.javaee.artastic.Artastic.domain.Comments;
import com.javaee.artastic.Artastic.domain.Error;
import com.javaee.artastic.Artastic.domain.Follow;
import com.javaee.artastic.Artastic.domain.Likes;
import com.javaee.artastic.Artastic.domain.Notification;
import com.javaee.artastic.Artastic.domain.UserDetails;
import com.javaee.artastic.Artastic.service.ArtworksService;
import com.javaee.artastic.Artastic.service.UsersService;
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
	
	@Autowired
	private UsersService usersService;
	
//	@RequestMapping(value="/community/{postType}")
//	@ResponseBody
//	public ModelAndView showArtwork(@PathVariable("postType")String postType) {
//		return new ModelAndView("community");
//	}
	
	@RequestMapping(value="/getPosts")
	@ResponseBody
	public ArtworksList getArtWorksAll(@RequestHeader HttpHeaders headers) {
		ArtworksList artworksList = new ArtworksList();
		
		try {
			
			int pageNo = 0;
			String pageStr = headers.getFirst("page");
			if(pageStr != null && !pageStr.equals("")) {
				pageNo = Integer.parseInt(pageStr);
			}
			
			Pageable pageable = new PageRequest(pageNo, 10);
			Page<Integer> page = null;
			String typeSort = headers.getFirst("present");
			int clientId = Integer.parseInt(headers.getFirst("userId"));
			if(typeSort.equals("popular")) {
				page = artworkService.findAllLikeSort(pageable);
			} else if (typeSort.equals("latest")) {
				page = artworkService.findAllTimeSort(pageable);
			} else if (typeSort.equals("feed")) {
				page = artworkService.findFollowArtworks(clientId, pageable);
			} else if (typeSort.equals("mylikes")) {
				//page = artworkService.findAllRandSort(pageable);
				int userId = Integer.parseInt(headers.getFirst("userId"));
				page = artworkService.findUserLikes(userId, pageable);
			} else if (typeSort.equals("tweet")) {
				page = artworkService.findAllCommentSort(pageable);
			} else {
				page = artworkService.findAllRandSort(pageable);
			}
			
			List<ArtWorkDetails> artWorkDetails = new ArrayList<>();
			List<Integer> artworkIds = page.getContent();
			
			for(Integer integer : artworkIds) {
				artWorkDetails.add(artworkService.getArtworkDetails(integer, clientId));
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
			int clientId = Integer.parseInt(headers.getFirst("userId"));
			
			ArtWorkDetails artWorkDetails = artworkService.getArtworkDetails(artworkId, clientId);
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
			} else {
				System.out.println("已喜欢过该作品");
			}
			
		}catch (Exception e) {
			error.setError(true);
		}
		
		return error;
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
			if(comment == null || comment.equals("")) {
				error.setError(true);
				return error;
			}
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
			int clientId = Integer.parseInt(headers.getFirst("userId"));
			int pageNo = 0;
			String pageStr = headers.getFirst("page");
			if(pageStr != null && !pageStr.equals("")) {
				pageNo = Integer.parseInt(pageStr);
			}
			String[] strings = present.split("/");
			String searchType = strings[1];
			String searchKey = strings[2];
			Pageable pageable = new PageRequest(pageNo, 12);
			Page<Integer> page = null;
			
			if(searchType.equals("member")) {
				
				List<UserDetails> members = usersService.findUsers(searchKey, pageable);
				artworksList.setMembers(members);
				return artworksList;
				
			} else if(searchType.equals("thismember")){
				page = artworkService.findByArtistNameEX(searchKey, pageable);
			} else if(searchType.equals("thistag")){
				page = artworkService.findByTagNameEX(searchKey, pageable);
			} else if(searchType.equals("tag")){
				page = artworkService.findByTagName(searchKey, pageable);	
			} else {
				page = artworkService.findBySearchKey(searchKey, pageable);
//				Page<Integer> page = artworkService.findBySearchAll(searchKey, pageable);	
			}
			
			List<ArtWorkDetails> artWorkDetails = new ArrayList<>();
			List<Integer> artworkIds = page.getContent();
			
			for(Integer integer : artworkIds) {
				artWorkDetails.add(artworkService.getArtworkDetails(integer, clientId));
			}
			artworksList.setPosts(artWorkDetails);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExceptionUtil.handleException(e);
		}
		
		return artworksList;
		
	}
	
	@RequestMapping(value="getpostchart")
	@ResponseBody
	public ArtworksList getPostChart(@RequestHeader HttpHeaders headers) {
		
		ArtworksList artworksList = new ArtworksList();
		try {
			int artworkId = Integer.parseInt(headers.getFirst("present").split("/")[3]);
			System.out.println("artworkId:" + String.valueOf(artworkId));
			List<Object[]> datalist = artworkService.countClicksPerMonth(artworkId);
			List<Object[]> datalist2 = artworkService.countClicksBySex(artworkId);
			ChartData chartData = new ChartData();
			chartData.setData1(datalist);
			chartData.setData2(datalist2);
			artworksList.setChartdata(chartData);
			
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		
		return artworksList;
	}
	
	public Error pushNotification(String senderName, String receiverName, String notiContent) {
		Error error = new Error();
		error.setError(false);
		try {
			Notification notification = new Notification();
			notification.setSenderName(senderName);
			notification.setReceiverName(receiverName);
			notification.setNotiTime(new Timestamp(System.currentTimeMillis()));
			notification.setNotiState("0");
			notification.setNotiContent(notiContent);
			usersService.saveNotification(notification);
		} catch (Exception e) {
			// TODO: handle exception
			error.setError(true);
		}
		return error;
	}
	
	public ArtworksList pullNotification(String receiverName){
		ArtworksList artworksList = new ArtworksList();
		artworksList.setNotifications(usersService.findByReceiverName(receiverName));
		return artworksList;
	}
	
}
