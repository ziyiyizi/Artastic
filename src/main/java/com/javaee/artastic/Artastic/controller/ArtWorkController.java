package com.javaee.artastic.Artastic.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.javaee.artastic.Artastic.domain.ArtWorkDetails;
import com.javaee.artastic.Artastic.domain.ArtWorkLikes;
import com.javaee.artastic.Artastic.domain.ArtworksList;
import com.javaee.artastic.Artastic.domain.ChartData;
import com.javaee.artastic.Artastic.domain.Clicks;
import com.javaee.artastic.Artastic.domain.Comments;
import com.javaee.artastic.Artastic.domain.Likes;
import com.javaee.artastic.Artastic.service.ArtworksService;
import com.javaee.artastic.Artastic.service.UsersService;
import com.javaee.artastic.Artastic.utils.ExceptionUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ArtWorkController {
	@Autowired
	private ArtworksService artworkService;
	
	@Autowired
	private UsersService usersService;
	
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
			String userIdStr = headers.getFirst("userId");
			
			if(typeSort.equals("popular")) {
				page = artworkService.findAllLikeSort(pageable);
			} else if (typeSort.equals("latest")) {
				page = artworkService.findAllTimeSort(pageable);
			} else if (typeSort.equals("feed")) {
				int clientId = Integer.parseInt(userIdStr);
				page = artworkService.findFollowArtworks(clientId, pageable);
			} else if (typeSort.equals("mylikes")) {
				//page = artworkService.findAllRandSort(pageable);
				int userId = Integer.parseInt(userIdStr);
				page = artworkService.findUserLikes(userId, pageable);
			} else if (typeSort.equals("tweet")) {
				page = artworkService.findAllCommentSort(pageable);
			} else {
				page = artworkService.findAllRandSort(pageable);
			}
			
			List<ArtWorkDetails> artWorkDetails = new ArrayList<>();
			List<Integer> artworkIds = page.getContent();
			
			if(userIdStr.equals("null")) {
				for(Integer integer : artworkIds) {
					artWorkDetails.add(artworkService.getArtworkDetails(integer));
				}
			} else {
				int clientId = Integer.parseInt(userIdStr);
				for(Integer integer : artworkIds) {
					artWorkDetails.add(artworkService.getArtworkDetails(integer, clientId));
				}
			}

			artworksList.setPosts(artWorkDetails);
			
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		return artworksList;
	}
	
	@RequestMapping(value="/getpost")
	@ResponseBody
	public ArtworksList getArtworkOne(@RequestHeader HttpHeaders headers) {
		ArtworksList artworksList = new ArtworksList();
		try {
			int artworkId = Integer.parseInt(headers.getFirst("present"));
			String userIdStr = headers.getFirst("userId");
			ArtWorkDetails artWorkDetails = null;
			if(userIdStr.equals("null")) {
				artWorkDetails = artworkService.getArtworkDetails(artworkId);
			} else {
				int clientId = Integer.parseInt(userIdStr);				
				artWorkDetails = artworkService.getArtworkDetails(artworkId, clientId);
			}
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
			String userIdStr = headers.getFirst("userId");
			int userId = 114514;
			if(!userIdStr.equals("null")) {
				userId = Integer.valueOf(userIdStr);
			}
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
	public ArtworksList addLikes(@RequestHeader HttpHeaders headers) {
		ArtworksList artworksList = new ArtworksList();
		artworksList.setError(false);
		try {
			int userId = Integer.valueOf(headers.getFirst("userid"));
			int artworkId = Integer.valueOf(headers.getFirst("present"));
			
			if(artworkService.isLike(userId, artworkId) == false) {
				Likes likes = new Likes();
				likes.setArtworkId(artworkId);
				likes.setUserId(userId);
				likes.setLiketime(new Timestamp(System.currentTimeMillis()));
				artworkService.saveLike(likes);
				String senderName = headers.getFirst("username");
				String receiverName = usersService.findNameByWorkId(artworkId);
				String workName = artworkService.findNameByworkId(artworkId);
				usersService.pushNotification(senderName, receiverName, workName, "like", "", artworkId);
				System.out.println("add like success");
			}
			
		}catch (Exception e) {
			artworksList.setError(true);
		}	
		return artworksList;
	}
	
	@RequestMapping(value="/makecomment")
	public ArtworksList makeComment(HttpServletRequest request, @RequestHeader HttpHeaders headers) {
		ArtworksList artworksList = new ArtworksList();
		artworksList.setError(false);
		try {
			String commentorName = headers.getFirst("username");
			
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
			String responseTo = mRequest.getParameter("responseTo");
			String comment = mRequest.getParameter("comment");
			if(comment == null || comment.equals("")) {
				artworksList.setError(true);
				return artworksList;
			}
			int artworkId = Integer.parseInt(mRequest.getParameter("artworkId"));
			
			Comments comments = new Comments();
			comments.setArtworkId(artworkId);
			comments.setComment(comment);
			comments.setCommentorName(commentorName);
			comments.setUserName(responseTo);
			comments.setCommentTime(new Timestamp(System.currentTimeMillis()));
			artworkService.saveComment(comments);
			
			String workName = artworkService.findNameByworkId(artworkId);
			if(responseTo == null || responseTo.equals("")) {
				String receiverName = usersService.findNameByWorkId(artworkId);
				usersService.pushNotification(commentorName, receiverName, workName, "comment", comment, artworkId);
			} else {
				usersService.pushNotification(commentorName, responseTo, workName, "comment2", comment, artworkId);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			artworksList.setError(true);
		}
		
		return artworksList;
	}
	
	@RequestMapping(value="/getpostchart")
	@ResponseBody
	public ArtworksList getPostChart(@RequestHeader HttpHeaders headers) {
		
		ArtworksList artworksList = new ArtworksList();
		try {
			int artworkId = Integer.parseInt(headers.getFirst("present").split("/")[3]);
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
	
	@RequestMapping(value="/getrecommendtags")
	@ResponseBody
	public ArtworksList getRecommendTags(@RequestHeader HttpHeaders headers) {
		ArtworksList artworksList = new ArtworksList();
		try {
			String key = headers.getFirst("present");
			List<String> values = artworkService.findSimilarTagEX(key);
			if(values.get(0) == null || values.get(0).equals("")) {
				values.add("unknown");
			}
			artworksList.setValues(values);
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handleException(e);
		}
		
		return artworksList;
	}
}
